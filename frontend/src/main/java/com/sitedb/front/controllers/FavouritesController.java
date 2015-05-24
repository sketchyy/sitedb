package com.sitedb.front.controllers;

import com.sitedb.front.entities.Site;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;

import java.util.Collection;

/**
 * Created by sketchyy on 05.05.2015.
 */

@Controller
public class FavouritesController {

//    @RequestMapping(value = "/favourites", method = RequestMethod.PUT)
//    public ResponseEntity addFavourite(@RequestParam(value = "site") Long siteId) {
//        RestTemplate restTemplate = RestTemplateCreator.create();
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//
//        return restTemplate.exchange("http://localhost:8083/favourites?site="+siteId, HttpMethod.PUT, RequestEntity.EMPTY, String.class);
//    }

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

//    @RequestMapping(value = "/favourites", method = RequestMethod.DELETE)
//    public ResponseEntity deleteFavourite(@RequestParam(value = "site") Long siteId,
//                                          HttpServletRequest request) {
//        Long userId = SessionChecker.processIdFromRequest(request);
//
//        RestTemplate restTemplate = RestTemplateCreator.create();
//        return restTemplate.exchange(FrontURIs.FAVOURITE_URI, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class, userId, siteId);
//    }

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
