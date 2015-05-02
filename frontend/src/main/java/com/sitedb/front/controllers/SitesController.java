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
    public String sites(@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                        Model model) {
        RestTemplate restTemplate = restTemplate();

        ResponseEntity<PagedResources<Resource<Site>>> responseEntity = restTemplate.exchange(
                RestURIs.allSitesPaged, HttpMethod.GET, null,
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
        RestTemplate restTemplate = restTemplate();

        Map<String, Integer> vars = new HashMap<>();
        vars.put("siteId", id);

//        ResponseEntity<Resource<Site>> res
//                = restTemplate.exchange(RestURIs.site, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Site>>() {
//        }, vars);

        Site site = restTemplate.getForObject(RestURIs.site, Site.class, vars);
        model.addAttribute("site", site);
        return "site";
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
