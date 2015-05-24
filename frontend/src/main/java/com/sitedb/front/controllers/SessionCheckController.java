package com.sitedb.front.controllers;

import com.sitedb.front.entities.UserID;
import com.sitedb.front.utils.RestTemplateCreator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexander on 04.05.2015.
 */
@Controller
public class SessionCheckController {

    public static final String GETUSER_URL = "http://localhost:8085/session";

    @RequestMapping(value = "/getuser")
    @ResponseBody
    public UserID getUserId(HttpServletRequest request) {
        RestTemplate restTemplate = RestTemplateCreator.create();
        UserID account = null;
        HttpHeaders headers = new HttpHeaders();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("ISDB"))
                headers.add("Cookie", cookie.getValue());
        HttpEntity<String> request2 = new HttpEntity<String>(headers);
        try {
            ResponseEntity<UserID> response = restTemplate.exchange(
                    GETUSER_URL, HttpMethod.POST, request2,
                    UserID.class);
            account = response.getBody();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        System.out.println(account.getId());
        return account;
    }

}
