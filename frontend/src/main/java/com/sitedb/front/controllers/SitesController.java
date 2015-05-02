package com.sitedb.front.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitedb.front.RestURIs;
import com.sitedb.front.entities.Site;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
    public String sites(@RequestParam(value = "id", required = false ) Integer id, Model model) {
        RestTemplate restTemplate = restTemplate();

        if (id == null) {
            ResponseEntity<PagedResources<Resource<Site>>> responseEntity = restTemplate.exchange(
                    RestURIs.allSitesPaged, HttpMethod.GET, null,
                    new ParameterizedTypeReference<PagedResources<Resource<Site>>>() {
                    }, 0 , 1);

            System.out.println(responseEntity.getBody());

            PagedResources<Resource<Site>> resources = responseEntity.getBody();
            List<Resource<Site>> sites = new ArrayList(responseEntity.getBody().getContent());

            for (Resource<Site> rs : sites) {
                String href = rs.getLink("self").getHref();
                rs.getContent().setIdByLink(href);
            }

            System.out.println(resources.getContent());
            System.out.println("next " + resources.getNextLink());
            System.out.println("prev " + resources.getPreviousLink());
            System.out.println("id " +sites.get(0));
            System.out.println("id " +sites.get(0).getId().getVariableNames());

            model.addAttribute("sites", sites);
            return "sites";
        } else {
            Map<String, Integer> vars = new HashMap<>();
            vars.put("siteId", id);

            ResponseEntity<Resource<Site>> res
                    = restTemplate.exchange(RestURIs.site, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Site>>() {
            }, vars);
            System.out.println("body " + res.getBody());
            System.out.println("links " + res.getBody().getLinks());
            System.out.println("content " + res.getBody().getContent());

            Site site = restTemplate.getForObject(RestURIs.site, Site.class, vars);

            model.addAttribute("site", site);
            return "site";
        }
    }

    private RestTemplate restTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
        converter.setObjectMapper(mapper);
        return new RestTemplate(Arrays.asList(converter));
    }
}
