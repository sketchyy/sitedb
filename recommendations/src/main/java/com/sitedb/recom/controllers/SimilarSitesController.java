package com.sitedb.recom.controllers;

import com.sitedb.recom.entities.Site;
import com.sitedb.recom.utils.RestTemplateCreator;
import com.sitedb.recom.utils.RestURIs;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sketchyy on 04.05.2015.
 */

@RestController("/")
public class SimilarSitesController {

    @RequestMapping("/similar")
    public List<Site> getSimilarSites(@RequestParam("site") Integer siteId) {

        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<PagedResources<Resource<Site>>> responseEntity = restTemplate.exchange(
                RestURIs.ALL_SITES_PAGED, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Resource<Site>>>() {
                }, 0, 20);

        List<Resource<Site>> resSites = new ArrayList(responseEntity.getBody().getContent());
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
