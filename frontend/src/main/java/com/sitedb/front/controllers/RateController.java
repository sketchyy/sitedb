package com.sitedb.front.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sitedb.front.RestTemplateCreator;
import com.sitedb.front.FrontURIs;
import com.sitedb.front.entities.Rate;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sketchyy on 03.05.2015.
 */

@Controller
public class RateController {

    @RequestMapping(value = "/rates", method = RequestMethod.POST)
    @ResponseBody
    public Rate setRate(@RequestParam(value = "site") Integer siteId,
                          @RequestParam(value = "rate") Integer rate) {
        ObjectMapper objectMapper = objectMapperInit();

        ObjectNode rateNode = objectMapper.createObjectNode();
        rateNode.put("rate", rate);
        rateNode.put("user", "http://localhost:8080/users/1"); //todo: take current user from session service
        rateNode.put("site", FrontURIs.ALL_SITES + "/" + siteId);

        RestTemplate restTemplate = RestTemplateCreator.create();
        URI rateURI = restTemplate.postForLocation(FrontURIs.ALL_RATES, rateNode);
        System.out.println(rateURI.toString());

        Rate createdRate = new Rate();
        createdRate.setRate(rate);
        createdRate.setIdByHref(rateURI.toString());
//        return new ResponseEntity<>(HttpStatus.CREATED);
        return createdRate;
    }

    @RequestMapping(value = "/rates", method = RequestMethod.PUT)
    @ResponseBody
    public Rate updateRate(@RequestParam(value = "rateId") Integer rateId,
                           @RequestParam(value = "rate") Integer rate) {
        String rateUri = FrontURIs.ALL_RATES + "/" + rateId;
        ObjectMapper objectMapper = objectMapperInit();

        ObjectNode rateNode = objectMapper.createObjectNode();
        rateNode.put("rate", rate);

        RestTemplate restTemplate = RestTemplateCreator.create();
        restTemplate.put(rateUri, rateNode);

        Rate createdRate = new Rate();
        createdRate.setRate(rate);
        createdRate.setIdByHref(rateUri);
        return createdRate;
    }

    @RequestMapping(value = "/avgrating", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getAvgRating(@RequestParam(value = "site") Integer siteId) {
        Map<String, String> result = new HashMap<>();
        RestTemplate restTemplate = RestTemplateCreator.create();

        // load avg
        ResponseEntity<Double> avgResp = restTemplate.getForEntity(FrontURIs.GET_AVG_RATING_URI, Double.class, siteId);
        NumberFormat formatter = new DecimalFormat("#0.00");
        Double avg = (avgResp.getBody() == null) ? 0.0 : avgResp.getBody();
        result.put("avg", formatter.format(avg));

        // load voters count
        ResponseEntity<Integer> cntResp = restTemplate.getForEntity(FrontURIs.GET_VOTERS_COUNT_URI, Integer.class, siteId);
        Integer cnt = (cntResp.getBody() == null) ? 0 : cntResp.getBody();
        result.put("cnt", cnt.toString());

        return result;
    }

    private ObjectMapper objectMapperInit() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new Jackson2HalModule());
        return objectMapper;
    }

}
