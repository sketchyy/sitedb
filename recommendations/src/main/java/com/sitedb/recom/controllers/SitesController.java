package com.sitedb.recom.controllers;

import com.sitedb.recom.entities.Site;
import com.sitedb.recom.entities.Tag;
import com.sitedb.recom.utils.RecomURIs;
import com.sitedb.recom.utils.RestTemplateCreator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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
 * Created by sketchyy on 04.05.2015.
 */

@RestController
public class SitesController {

    @RequestMapping("/sites")
    public List<Site> getAllSites(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<PagedResources<Resource<Site>>> responseEntity = restTemplate.exchange(
                RecomURIs.SITES_PAGED_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Resource<Site>>>() {
                }, page, size);

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

    @RequestMapping("/sites/{siteId}")
    public Site getSiteById(@PathVariable("siteId") Long siteId) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<Resource<Site>> responseEntity = restTemplate.exchange(
                RecomURIs.SITE_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resource<Site>>() {
                }, siteId);

        Resource<Site> respSite = responseEntity.getBody();
        Site site = respSite.getContent();
        String href = respSite.getLink("self").getHref();
        // initialize href to front
        site.setIdByLink(href);
        return site;
    }

    @RequestMapping("/similar")
    public List<Site> getSimilarSites(@RequestParam("site") Long siteId) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<PagedResources<Resource<Site>>> responseEntity = restTemplate.exchange(
                RecomURIs.SIMILAR_SITES_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Resource<Site>>>() {
                }, siteId);

        List<Resource<Site>> resSites = new ArrayList<>(responseEntity.getBody().getContent());
        List<Site> sites = new ArrayList<>(resSites.size());

        for (Resource<Site> rs : resSites) {
            String href = rs.getLink("self").getHref();
            // initialize href to front, not to backend
            rs.getContent().setIdByLink(href);
            // and store it with Site
            sites.add(rs.getContent());
        }

        return sites;
    }

    @RequestMapping("/sites/{site}/tags")
    public List<Tag> getTagBySite(@PathVariable("site") Long siteId) {
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<Resources<Resource<Tag>>> responseEntity = restTemplate.exchange(
                RecomURIs.TAGS_BY_SITE_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Resource<Tag>>>() {
                }, siteId);

        System.out.println(responseEntity.getBody());

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

    @RequestMapping("/topsites")
    public List<Site> getTopSites() {
        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<PagedResources<Resource<Site>>> responseEntity = restTemplate.exchange(
                RecomURIs.SIMILAR_SITES_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Resource<Site>>>() {
                });

        List<Resource<Site>> resSites = new ArrayList<>(responseEntity.getBody().getContent());
        List<Site> sites = new ArrayList<>(resSites.size());

        for (Resource<Site> rs : resSites) {
            String href = rs.getLink("self").getHref();
            // initialize href to front, not to backend
            rs.getContent().setIdByLink(href);
            // and store it with Site
            sites.add(rs.getContent());
        }

        return sites;
    }

}
