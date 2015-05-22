package com.sitedb.controllers;

import com.sitedb.UsersURIs;
import com.sitedb.Utils.RestTemplateCreator;
import com.sitedb.entities.Site;
import com.sitedb.entities.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sketchyy on 15.05.2015.
 */

@RestController
public class UsersController {

    @RequestMapping("/users/{userId}")
    public User getSiteById(@PathVariable("userId") Long userId) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<Resource<User>> responseEntity = restTemplate.exchange(
                UsersURIs.USER_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resource<User>>() {
                }, userId);

        Resource<User> respSite = responseEntity.getBody();
        User user = respSite.getContent();
        String href = respSite.getLink("self").getHref();
        // initialize href to front
        user.setHrefToFront(href);
        return user;
    }


    @RequestMapping(value = "/users/{userId}/favourites", method = RequestMethod.GET)
    @ResponseBody
    public List<Site> favouritesByUser(@PathVariable(value = "userId") Integer userId) {
        // Get all favs
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<PagedResources<Resource<Site>>> responseEntity = restTemplate.exchange(
                UsersURIs.ALL_FAVOURITES_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Resource<Site>>>() {
                }, userId);

        List<Resource<Site>> respSites = new ArrayList<>(responseEntity.getBody().getContent());

        return extractSites(respSites);
    }

    public List<Site> extractSites(List<Resource<Site>> respSites) {
        List<Site> sites = new ArrayList<>(respSites.size());

        for (Resource<Site> rs : respSites) {
            if (rs.hasLink("self")) {
                String href = rs.getLink("self").getHref();
                // initialize href to front, not to backend
                rs.getContent().setIdByLink(href);
            }
            // and store it with Site
            sites.add(rs.getContent());
        }
        return sites;
    }
}
