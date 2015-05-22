package com.sitedb.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sitedb.UsersURIs;
import com.sitedb.Utils.RestTemplateCreator;
import com.sitedb.entities.Comment;
import com.sitedb.entities.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sketchyy on 03.05.2015.
 */

@Controller
public class CommentsController {

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> getCommentsBySite(@RequestParam(value = "site") Integer siteId) {
        System.out.println("site = " + siteId);

        RestTemplate restTemplate = RestTemplateCreator.create();

        ResponseEntity<PagedResources<Resource<Comment>>> responseEntity = restTemplate.exchange(
                UsersURIs.COMMENTS_BY_SITE_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Resource<Comment>>>() {
                }, siteId);

        List<Resource<Comment>> respComments = new ArrayList<>(responseEntity.getBody().getContent());
        List<Comment> comments = new ArrayList<>(respComments.size());

        for (Resource<Comment> commentResp : respComments) {
            Comment comment = commentResp.getContent();

            String href = commentResp.getLink("self").getHref();
            // initialize comment id
            comment.setIdByLink(href);

            // load user to every comment
            String linkToUser = commentResp.getLink("user").getHref();

            ResponseEntity<Resource<User>> userResponse = restTemplate.exchange(
                    linkToUser, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Resource<User>>() {
                    });

            User u = userResponse.getBody().getContent();
            u.setHrefToFront(userResponse.getBody().getLink("self").getHref());
            comment.setUser(u);

            // and store it with Site
            comments.add(comment);
        }

        return comments;
    }


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
        URI course1Uri = restTemplate.postForLocation(UsersURIs.ALL_COMMENTS_URI, commentNode);

        System.out.println(course1Uri.toString());
        return "redirect:" + "http://localhost:8082/site?id=" + siteId;
    }


    @RequestMapping(value = "/comment/{comment}", method = RequestMethod.DELETE)
    public ResponseEntity removeComment(@PathVariable(value = "comment") Long commentId) {
        System.out.println("commentid = " + commentId);

        RestTemplate restTemplate = RestTemplateCreator.create();

        return restTemplate.exchange(
                UsersURIs.COMMENT_URI,
                HttpMethod.DELETE,
                RequestEntity.EMPTY,
                ResponseEntity.class,
                commentId);
    }



}
