package com.security.demo.config;

import com.security.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
    @Autowired
    private UserService userService;
 
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http          
          .authorizeRequests()
            .antMatchers(
                "/build/**", 
                "/register", 
                "/login*", 
                "/about", 
                "/greeting",
                "/h2-console/**",
                "/webjars/**",
                "/terms"
              ).permitAll() //allows access to all build resources
            .anyRequest().authenticated()
            .and()
          .formLogin()
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/about", true)
            .and()
          .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout")
            .and()
          .csrf().disable()
          //added for access to h2-console
          .headers().frameOptions().disable();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}