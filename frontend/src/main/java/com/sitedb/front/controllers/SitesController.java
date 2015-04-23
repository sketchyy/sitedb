package com.sitedb.front.controllers;

import com.sitedb.front.entities.Site;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sketchyy on 24.04.2015.
 */

@Controller
public class SitesController {

    @RequestMapping("/sites")
    public String sites(@RequestParam("id") String id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Site site = restTemplate.getForObject(String.format("http://localhost:8080/sites/%s", id), Site.class);

        model.addAttribute("name", site.getName());
        model.addAttribute("url", site.getUrl());
        return "sites";
    }
}
