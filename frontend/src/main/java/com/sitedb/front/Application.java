package com.sitedb.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by sketchyy on 23.04.2015.
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("LOLO");
//        RestTemplate restTemplate = new RestTemplate();
//        Site page = restTemplate.getForObject("http://localhost:8080/sites/1", Site.class);
//        System.out.println("Name:    " + page.getName());
//        System.out.println("Url:   " + page.getUrl());
    }
}
