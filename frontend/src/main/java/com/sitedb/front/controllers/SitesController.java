package com.sitedb.front.controllers;

import com.sitedb.front.entities.*;
import com.sitedb.front.utils.FrontURIs;
import com.sitedb.front.utils.RestTemplateCreator;
import com.sitedb.front.utils.SessionChecker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sketchyy on 24.04.2015.
 */

@Controller
public class SitesController {

    @RequestMapping(value = {"/sites", "/"})
    public String sites(@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                        HttpServletRequest request,
                        Model model) {
        User user = SessionChecker.processUserFromRequest(request);

        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<List<Site>> responseEntity = restTemplate.exchange(FrontURIs.ALL_SITES_PAGED,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Site>>() {
                }, page, size);

        model.addAttribute("sites", responseEntity.getBody());
        model.addAttribute("user", user);
        return "sites";
    }

    @RequestMapping("/site")
    public String site(@RequestParam(value = "id") Long siteId,
                       HttpServletRequest request,
                       Model model) {
        User user = SessionChecker.processUserFromRequest(request);

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

        System.out.println("before");
        ResponseEntity<List<Comment>> commentsResponse = restTemplate.exchange(FrontURIs.COMMENTS_BY_SITE_URI,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {
                }, site.getId());
        Collection<Comment> comments = commentsResponse.getBody();
        System.out.println("after");

        // Does user already rates this site?
        Rate rate = loadRate(restTemplate, site.getId(), user);

        // Is in favourite?
        boolean isFav = isInFavourite(restTemplate, siteId, user);

        // Load similar sites
        System.out.println("111");
        ResponseEntity<List<Site>> similarSites = restTemplate.exchange(FrontURIs.SIMILAR_SITES_URI,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Site>>() {
                }, site.getId());


        System.out.println("SIMILAR : " + similarSites.getBody());
        // add Site and Tags to model
        model.addAttribute("site", site);
        model.addAttribute("tags", tags);
        model.addAttribute("comments", comments);
        model.addAttribute("rate", rate);
        model.addAttribute("similar", similarSites.getBody());
        model.addAttribute("isFav", isFav);
        model.addAttribute("user", user);
        return "site";
    }


    private boolean isInFavourite(RestTemplate restTemplate, long siteId, User user) {
        // if no user
        if (user == null) {
            return false;
        }
        Integer resp = restTemplate.getForObject(FrontURIs.IS_SITE_IN_FAVS_URI, Integer.class, siteId, user.getId());
        return resp > 0;
    }

    private Rate loadRate(RestTemplate restTemplate, long siteId, User user) {
        // if no user
        if (user == null) {
            return new Rate();
        }

        ResponseEntity<Resources<Resource<Rate>>> response
                = restTemplate.exchange(FrontURIs.FIND_RATE_BY_SITE_AND_USER,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Resources<Resource<Rate>>>() {
                },
                siteId,
                user.getId());
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

    private Collection<Tag> loadTags(RestTemplate restTemplate, String hrefToTags) {
        ResponseEntity<Resources<Tag>> tagsResponse = restTemplate.exchange(
                hrefToTags, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Tag>>() {
                });
        System.out.println("load tags response = " + tagsResponse.getBody());
        return tagsResponse.getBody().getContent();
    }
}
