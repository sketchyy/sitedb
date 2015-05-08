package com.sitedb.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by sketchyy on 23.04.2015.
 */

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        SecurityContextHolder.getContext().getAuthentication();
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("hello");
        registry.addViewController("/hello").setViewName("hello");
//        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
    }

}
