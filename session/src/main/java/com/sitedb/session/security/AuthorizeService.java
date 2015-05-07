package com.sitedb.session.security;

import com.sitedb.session.entities.Session;
import com.sitedb.session.repositories.SessionRepository;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * Created by Alexander on 07.05.2015.
 */
@Controller
public class AuthorizeService {

    public final static long LIFE_TIME = 1209600;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @RequestMapping(value = "/getlogin", method = RequestMethod.POST)
    public String GetLogin(@RequestParam(value = "login") String login,
                           @RequestParam(value = "password") String password,
                           HttpServletResponse response) {
        User user = userRepository.findByLoginAndPasswordHash(login, password);
        if (user == null)
            return "redirect:" + "http://localhost:8082/login?error";
        Session session = new Session();
        session.setUserId(user.getUser_id());
        session.setExpires(new Timestamp(System.currentTimeMillis() + LIFE_TIME));
        session.setSessionId(Base64.encode((login + password + session.getExpires().toString()).getBytes()));
        sessionRepository.save(session);
        System.out.println(user);
        Cookie cookie = new Cookie("ISDB", session.getSessionId());
//        cookie.setMaxAge();
        response.addCookie(cookie);
        return "redirect:" + "http://localhost:8082/hello";
    }

}
