package com.security.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.security.demo.dto.UserRegistrationDto;
import com.security.demo.mappers.UserMapper;
import com.security.demo.model.Role;
import com.security.demo.model.User;
import com.security.demo.repository.UserRepository;
import com.security.demo.validation.UserAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User registerNewUser(UserRegistrationDto registration) throws UserAlreadyExistsException {
        if( emailExists(registration.getEmail()) ){  
            throw new UserAlreadyExistsException(
                    "There is an account with that email address: " 
                +  registration.getEmail());
        }

        User user = userMapper.userRegistrationDtoToUser(registration);

        user.setDeletedFlag(0);
        user.setDisabledFlag(0);
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));

        return save(user);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public long count(){
        return userRepository.count();
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}