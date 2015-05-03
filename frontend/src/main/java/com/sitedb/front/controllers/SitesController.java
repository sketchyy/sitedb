package com.sitedb.front.controllers;

import com.sitedb.front.RestTemplateCreator;
import com.sitedb.front.RestURIs;
import com.sitedb.front.entities.Comment;
import com.sitedb.front.entities.Site;
import com.sitedb.front.entities.Tag;
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
        ResponseEntity<Resources<Tag>> tagsResponse = restTemplate.exchange(
                hrefToTags, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Tag>>() {
                });
        System.out.println("hrefToTags = " + hrefToTags);
        System.out.println("response = " + tagsResponse.getBody());
        Collection<Tag> tags = tagsResponse.getBody().getContent();

        // load comments
        String hrefToComments = res.getBody().getLink("comments").getHref();
        ResponseEntity<Resources<Comment>> commentsResponse = restTemplate.exchange(
                hrefToComments, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Comment>>() {
                });
        System.out.println("hrefToComments = " + hrefToComments);
        System.out.println("response = " + commentsResponse.getBody());
        Collection<Comment> comments = commentsResponse.getBody().getContent();

        // add Site and Tags to model
        model.addAttribute("site", site);
        model.addAttribute("tags", tags);
        model.addAttribute("comments", comments);
        return "site";
    }
}
