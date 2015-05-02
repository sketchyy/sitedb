package com.sitedb.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        RestTemplate restTemplate = new RestTemplate();
//        Site page = restTemplate.getForObject("http://localhost:8080/sites/1", Site.class);
//        System.out.println("Name:    " + page.getName());
//        System.out.println("Url:   " + page.getUrl());
    }
}
