package com.sitedb.front.controllers;

import com.sitedb.front.RestTemplateCreator;
import com.sitedb.front.FrontURIs;
import com.sitedb.front.entities.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by sketchyy on 24.04.2015.
 */

@Controller
public class SitesController {

    @RequestMapping("/sites")
    public String sites(@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                        Model model) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<List<Site>> responseEntity = restTemplate.exchange(FrontURIs.ALL_SITES_PAGED,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Site>>() {
                }, page, size);

        model.addAttribute("sites", responseEntity.getBody());
        return "sites";
    }

    @RequestMapping("/site")
    public String site(@RequestParam(value = "id") Long siteId, Model model) {
        RestTemplate restTemplate = RestTemplateCreator.create();
//        Map<String, Integer> vars = new HashMap<>();
//        vars.put("siteId", siteId);

        // load Site from db-controller
        Site site = restTemplate.getForObject(FrontURIs.SITE, Site.class, siteId);

        // load tags
//        String hrefToTags = res.getBody().getLink("tags").getHref();
//        Collection<Tag> tags = loadTags(restTemplate, hrefToTags);
        ResponseEntity<List<Tag>> tagsBySiteResponse = restTemplate.exchange(FrontURIs.TAGS_BY_SITE_URI,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Tag>>() {
                }, site.getId());
        Collection<Tag> tags = tagsBySiteResponse.getBody();

        // load comments
        String hrefToComments = /*res.getBody().getLink("comments").getHref()*/
                "http://localhost:8080/sites/" + site.getId() + "/comments";
        Collection<Comment> comments = loadComments(restTemplate, hrefToComments);

        // Does user already rates this site?
        Rate rate = loadRate(restTemplate, site.getId(), 1); // todo load current user id

        // Load similar sites
        System.out.println("111");
        ResponseEntity<List<Site>> similarSites = restTemplate.exchange(FrontURIs.SIMILAR_SITES_URI,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Site>>() {
                }, site.getId());

        // Is in favourite?
        boolean isFav = isInFavourite(restTemplate, siteId, 1); // todo load current user id

        System.out.println("SIMILAR : " + similarSites.getBody());
        // add Site and Tags to model
        model.addAttribute("site", site);
        model.addAttribute("tags", tags);
        model.addAttribute("comments", comments);
        model.addAttribute("rate", rate);
        model.addAttribute("similar", similarSites.getBody());
        model.addAttribute("isFav", isFav);
        return "site";
    }


    private boolean isInFavourite(RestTemplate restTemplate, long siteId, long userId) {
        Integer resp = restTemplate.getForObject(FrontURIs.IS_SITE_IN_FAVS_URI, Integer.class, siteId, userId);
        return resp > 0;
    }

    private Rate loadRate(RestTemplate restTemplate, long siteId, long userId) {
        ResponseEntity<Resources<Resource<Rate>>> response
                = restTemplate.exchange(FrontURIs.FIND_RATE_BY_SITE_AND_USER,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Resources<Resource<Rate>>>() {
                },
                siteId,
                userId);
        List<Resource<Rate>> rates = new ArrayList<>(response.getBody().getContent());

        if (rates.isEmpty()) {
            return new Rate();
        }

        Rate r = rates.get(0).getContent();
        if (rates.get(0).hasLink("self")) {
            r.setIdByHref(rates.get(0).getLink("self").getHref());
        }

        return r;
    }

    private Collection<Comment> loadComments(RestTemplate restTemplate, String hrefToComments) {
        ResponseEntity<Resources<Resource<Comment>>> commentsResponse = restTemplate.exchange(
                hrefToComments, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Resource<Comment>>>() {
                });
//        System.out.println("load comments response = " + commentsResponse.getBody());

        Collection<Resource<Comment>> commentsResources = commentsResponse.getBody().getContent();
        Collection<Comment> comments = new ArrayList<>(commentsResources.size());

        // load user to every comment
        for (Resource<Comment> comRes : commentsResources) {
            String linkToUser = comRes.getLink("user").getHref();

            ResponseEntity<Resource<User>> userResponse = restTemplate.exchange(
                    linkToUser, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Resource<User>>() {
                    });

            User u = userResponse.getBody().getContent();
            u.setHrefToFront(userResponse.getBody().getLink("self").getHref());
            comRes.getContent().setUser(u);

            comments.add(comRes.getContent());
        }
        return comments;
    }

    private Collection<Tag> loadTags(RestTemplate restTemplate, String hrefToTags) {
        ResponseEntity<Resources<Tag>> tagsResponse = restTemplate.exchange(
                hrefToTags, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Tag>>() {
                });
        System.out.println("load tags response = " + tagsResponse.getBody());
        return tagsResponse.getBody().getContent();
    }
}
