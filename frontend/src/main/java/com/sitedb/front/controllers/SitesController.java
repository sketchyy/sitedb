package com.sitedb.front.controllers;

import com.sitedb.front.RestTemplateCreator;
import com.sitedb.front.RestURIs;
import com.sitedb.front.entities.Comment;
import com.sitedb.front.entities.Site;
import com.sitedb.front.entities.Tag;
import com.sitedb.front.entities.User;
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

        ResponseEntity<PagedResources<Resource<Site>>> responseEntity = restTemplate.exchange(
                RestURIs.ALL_SITES_PAGED, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Resource<Site>>>() {
                }, page, size);

        List<Resource<Site>> resSites = new ArrayList(responseEntity.getBody().getContent());
        List<Site> sites = new ArrayList<>(resSites.size());

        for (Resource<Site> rs : resSites) {
            String href = rs.getLink("self").getHref();
            // initialize href to front, not to backend
            rs.getContent().setIdByLink(href);
            // and store it with Site
            sites.add(rs.getContent());
        }

        model.addAttribute("sites", sites);
        return "sites";
    }

    @RequestMapping("/site")
    public String site(@RequestParam(value = "id") Integer id, Model model) {
        RestTemplate restTemplate = RestTemplateCreator.create();
        Map<String, Integer> vars = new HashMap<>();
        vars.put("siteId", id);

        // load Site from db-controller
        ResponseEntity<Resource<Site>> res
                = restTemplate.exchange(RestURIs.SITE, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Site>>() {
        }, vars);
        Site site = res.getBody().getContent();
        site.setIdByLink(res.getBody().getLink("self").getHref());

        // load tags
        String hrefToTags = res.getBody().getLink("tags").getHref();
        Collection<Tag> tags = loadTags(restTemplate, hrefToTags);

        // load comments
        String hrefToComments = res.getBody().getLink("comments").getHref();
        Collection<Comment> comments = loadComments(restTemplate, hrefToComments);

        // add Site and Tags to model
        model.addAttribute("site", site);
        model.addAttribute("tags", tags);
        model.addAttribute("comments", comments);
        return "site";
    }

    private Collection<Comment> loadComments(RestTemplate restTemplate, String hrefToComments) {
        ResponseEntity<Resources<Resource<Comment>>> commentsResponse = restTemplate.exchange(
                hrefToComments, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Resource<Comment>>>() {
                });
        System.out.println("load comments response = " + commentsResponse.getBody());

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
