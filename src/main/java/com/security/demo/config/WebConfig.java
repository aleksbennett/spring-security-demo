package com.security.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
 
    /* set default page to be index */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    /*
    Allow Thymeleaf resources like css and js to be accessible from /resources/static/build/ 
    which is now mapped to /build/ for it's url. 
    Allow access to webjars for jquery and bootstrap
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/build/**", "/webjars/**")
            .addResourceLocations("classpath:/static/build/", "/webjars/");
    }
}