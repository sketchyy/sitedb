package com.sitedb.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sketchyy on 24.05.2015.
 */

@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Set max-age -> 0 to session cookies, to remove it
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if ("ISDB".equals(cookies[i].getName())) {
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        return "redirect:" + request.getHeader("Referer");
    }

}
