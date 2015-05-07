package com.sitedb.front.controllers;

import com.sitedb.front.RestTemplateCreator;
import com.sitedb.front.FrontURIs;
import com.sitedb.front.entities.Site;
import com.sitedb.front.entities.Tag;
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
 * Created by sketchyy on 06.05.2015.
 */

@Controller
public class TagController {

    @RequestMapping("/tag")
    public String sites(@RequestParam(value = "id") Long tagId,
                        Model model) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        Tag tag = restTemplate.getForObject(FrontURIs.TAG_URI, Tag.class, tagId);

        ResponseEntity<List<Site>> sitesByTag = restTemplate.exchange(FrontURIs.SITES_BY_TAG_URI,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Site>>() {
                }, tagId);

        model.addAttribute("sites", sitesByTag.getBody());
        model.addAttribute("tag", tag);
        return "tag";
    }

}
