package com.sitedb.recom.controllers;

import com.sitedb.recom.entities.Site;
import com.sitedb.recom.entities.Tag;
import com.sitedb.recom.utils.RecomURIs;
import com.sitedb.recom.utils.RestTemplateCreator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sketchyy on 06.05.2015.
 */

@RestController
public class TagController {

    @RequestMapping("/tags")
    public List<Tag> getTags(@RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<PagedResources<Resource<Tag>>> responseEntity = restTemplate.exchange(
                RecomURIs.TAGS_PAGED_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Resource<Tag>>>() {
                }, page, size);

        List<Resource<Tag>> respTags = new ArrayList<>(responseEntity.getBody().getContent());
        List<Tag> tags = new ArrayList<>(respTags.size());

        for (Resource<Tag> rt : respTags) {
            String href = rt.getLink("self").getHref();
            // initialize href to front, not to backend
            rt.getContent().setIdByLink(href);
            // and store it with Site
            tags.add(rt.getContent());
        }

        return tags;
    }

    @RequestMapping("/tags/{tagId}")
    public Tag getTag(@PathVariable("tagId") Long tagId) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<Resource<Tag>> tagResponse = restTemplate.exchange(
                RecomURIs.TAG_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resource<Tag>>() {
                }, tagId);

        Tag tag = null;
        if (tagResponse.getBody() != null) {
            String href = tagResponse.getBody().getLink("self").getHref();
            // initialize href to front
            tagResponse.getBody().getContent().setIdByLink(href);
            tag = tagResponse.getBody().getContent();
        }

        return tag;
    }

    @RequestMapping("/tags/{tag}/sites")
    public List<Site> getSitesByTag(@PathVariable("tag") Long tagId) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<PagedResources<Resource<Site>>> responseEntity = restTemplate.exchange(
                RecomURIs.SITES_BY_TAG_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Resource<Site>>>() {
                }, tagId);

        List<Resource<Site>> respSites = new ArrayList<>(responseEntity.getBody().getContent());
        List<Site> sites = new ArrayList<>(respSites.size());

        for (Resource<Site> rs : respSites) {
            String href = rs.getLink("self").getHref();
            // initialize href to front, not to backend
            rs.getContent().setIdByLink(href);
            // and store it with Site
            sites.add(rs.getContent());
        }

        return sites;
    }

}
