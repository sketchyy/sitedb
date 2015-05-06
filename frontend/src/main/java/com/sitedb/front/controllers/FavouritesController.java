package com.sitedb.front.controllers;

import com.sitedb.front.RestTemplateCreator;
import com.sitedb.front.FrontURIs;
import com.sitedb.front.entities.Site;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collection;

/**
 * Created by sketchyy on 05.05.2015.
 */

@Controller
public class FavouritesController {

    @RequestMapping(value = "/favourites", method = RequestMethod.PUT)
    public ResponseEntity addFavourite(@RequestParam(value = "site") Long siteId) {
        RestTemplate restTemplate = RestTemplateCreator.create();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));

        // Get all favs
        ResponseEntity<Resources<Resource<Site>>> favouritesResp = restTemplate.exchange(FrontURIs.ALL_FAVOURITES_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Resource<Site>>>() {
                }, 1 /* userId */);

        // Add new link to favourite list
        String siteLinks = buildURIList(favouritesResp.getBody().getContent(), FrontURIs.ALL_SITES + "/" + siteId);

        // Send PUT to db-controller
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/uri-list");
        HttpEntity<Object> request = new HttpEntity<>(siteLinks, headers);

        System.out.println("LNKS =  \n" + siteLinks);
        return restTemplate.exchange(FrontURIs.ALL_FAVOURITES_URI, HttpMethod.PUT, request, String.class, 1 /* userId */);
    }

//    @RequestMapping(value = "/favourite", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, String> checkFavourite(@RequestParam(value = "site") Integer siteId) {
//        Map<String,String> result = new HashMap<>();
//        // Get all favs
//        RestTemplate restTemplate = RestTemplateCreator.create();
//        ResponseEntity<Resources<Resource<Site>>> favouritesResp = restTemplate.exchange(RestURIs.ALL_FAVOURITES_URI, HttpMethod.GET, null,
//                new ParameterizedTypeReference<Resources<Resource<Site>>>() {
//                }, 1 /* userId */);
//
//
//        return result;
//    }

    @RequestMapping(value = "/favourites", method = RequestMethod.DELETE)
    public ResponseEntity deleteFavourite(@RequestParam(value = "site") Long siteId) {
        RestTemplate restTemplate = RestTemplateCreator.create();
        return restTemplate.exchange(FrontURIs.FAVOURITE_URI, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class, 1, siteId); // todo userid
    }

    private String buildURIList (Collection<Resource<Site>> sites, String newFavouriteLink) {
        StringBuilder result = new StringBuilder();
        for (Resource<Site> site : sites) {
            result.append(site.getLink("self").getHref());
            result.append("\n");
        }
        result.append(newFavouriteLink);
        return result.toString();
    }
}
