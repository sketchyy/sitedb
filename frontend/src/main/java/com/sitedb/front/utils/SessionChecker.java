package com.sitedb.front.utils;

import com.sitedb.front.entities.User;
import com.sitedb.front.entities.UserID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by sketchyy on 14.05.2015.
 */

public final class SessionChecker {

    private static final String GETUSER_URL = "http://localhost:8085/session";

    private SessionChecker() {
    }

    public static long processIdFromRequest(HttpServletRequest request) {
        System.out.println("Cookie = " + request.getHeader("Cookie"));

        RestTemplate restTemplate = RestTemplateCreator.create();
        UserID account = null;
        HttpHeaders headers = new HttpHeaders();
        Cookie[] cookies = request.getCookies() != null ? request.getCookies() : new Cookie[0];
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("ISDB"))
                headers.add("Cookie", cookie.getValue());
        HttpEntity<String> request2 = new HttpEntity<>(headers);
        try {
            ResponseEntity<UserID> response = restTemplate.exchange(
                    GETUSER_URL, HttpMethod.POST, request2,
                    UserID.class);
            account = response.getBody();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        System.out.println(account.getId());
        return account.getId();
//        return 0; // no user
    }

    public static User processUserFromRequest(HttpServletRequest request) {
        long userId = processIdFromRequest(request);
        System.out.println("userid = " + userId);

        // If user not authorized
        if (userId <= 0) {
            System.out.println("userid = " + userId);
            return null;
        }

        RestTemplate restTemplate = RestTemplateCreator.create();
        User user = restTemplate.getForObject(FrontURIs.USER_URI, User.class, userId);
        System.out.println("user " + user);

        return user;
    }

}
