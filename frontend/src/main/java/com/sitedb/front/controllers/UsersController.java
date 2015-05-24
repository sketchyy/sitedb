package com.sitedb.front.controllers;

import com.sitedb.front.entities.Site;
import com.sitedb.front.entities.User;
import com.sitedb.front.utils.FrontURIs;
import com.sitedb.front.utils.RestTemplateCreator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by sketchyy on 20.05.2015.
 */

@Controller
public class UsersController {

    @RequestMapping("/user")
    public String sites(@RequestParam(value = "id") Long userId,
                        Model model) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        User user = restTemplate.getForObject(FrontURIs.USER_URI, User.class, userId);

        ResponseEntity<List<Site>> responseEntity = restTemplate.exchange(FrontURIs.ALL_FAVOURITES_URI,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Site>>() {
                }, userId);

        model.addAttribute("user", user);
        model.addAttribute("favourites", responseEntity.getBody());
        return "user";
    }
}
