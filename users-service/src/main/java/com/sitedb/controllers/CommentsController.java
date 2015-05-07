package com.sitedb.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sitedb.UsersURIs;
import com.sitedb.Utils.RestTemplateCreator;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sketchyy on 03.05.2015.
 */

@Controller
public class CommentsController {

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String postComment(@RequestParam(value = "comment") String comment,
                              @RequestParam(value = "site") Integer siteId) {
        System.out.println("site = " + siteId);
        System.out.println("comment = " + comment);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new Jackson2HalModule());

        ObjectNode commentNode = objectMapper.createObjectNode();
        commentNode.put("text", comment);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        commentNode.put("time", df.format(new Date()));
        commentNode.put("user", "http://localhost:8080/users/1"); //todo: take current user from session service
        commentNode.put("site", UsersURIs.ALL_SITES + "/" + siteId);

        System.out.println(commentNode);
        RestTemplate restTemplate = RestTemplateCreator.create();
        URI course1Uri = restTemplate.postForLocation(UsersURIs.ALL_COMMENTS, commentNode);

        System.out.println(course1Uri.toString());
        return "redirect:" + "http://localhost:8082/site?id=" + siteId;
    }

}
