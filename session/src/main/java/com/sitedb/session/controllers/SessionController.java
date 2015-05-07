package com.sitedb.session.controllers;

import com.sitedb.session.entities.Session;
import com.sitedb.session.entities.UserID;
import com.sitedb.session.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Timestamp;

/**
 * Created by Alexander on 04.05.2015.
 */

@Controller
public class SessionController {

    @Autowired
    DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Autowired
    private SessionRepository sessionRepository;

    @RequestMapping(value = "/session")
    @ResponseBody
    UserID checkSession(HttpEntity<String> request) {
        UserID result = new UserID();
        try {
            HttpHeaders httpHeaders = request.getHeaders();
            String sessionCookie = httpHeaders.get("Cookie").get(0);
            Session session = null;
            if (sessionCookie != null) {
                System.out.println(sessionCookie);
                session = sessionRepository.findBySessionId(sessionCookie);
                System.out.println(session.getUserId());
                if (session.getExpires().before(new Timestamp(System.currentTimeMillis()))) {
                    session = null;
                    result.setId(-2); // -2 mean session is expired.
                    return result;
                }
            }
            if (session != null)
                result.setId(session.getUserId());
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setId(-1);
        }
        return result;
    }
}
