package com.sitedb.session.controllers;

import com.sitedb.session.entities.Role;
import com.sitedb.session.entities.User;
import com.sitedb.session.entities.UserInfo;
import com.sitedb.session.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexander on 08.05.2015.
 */
@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    public final static String URI = "http://localhost:8080/users/search/findByEmail?email={email}";

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String RegisterUser(@RequestParam("login") String login,
                               @RequestParam("password") String password,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("email") String email,
                               @RequestParam("birthday") String birthday,
                               @RequestParam("gender") String gender) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
        } catch (ParseException e) {
            System.out.println("Bad date");
            e.printStackTrace();
            return "redirect:" + "http://localhost:8082/register?baddate";
        }
        UserInfo userInfo = new UserInfo(name, surname, email, date, gender);
        HttpEntity<UserInfo> httpEntity = new HttpEntity<>(userInfo);
        RestTemplate restTemplate = RestTemplateCreator.create();
        ResponseEntity<Long> id = restTemplate.exchange("http://localhost:8080/users", HttpMethod.POST, httpEntity, Long.class);
        System.out.println(id.toString());
        HttpHeaders httpHeaders1 = new HttpHeaders();
        httpHeaders1.add("EMAIL", email);
        Long userId = null;
        try {
            ResponseEntity<Long> ID = restTemplate.getForEntity(URI, Long.class, email);
            userId = (ID.getBody() == null) ? 0L : ID.getBody();
            System.out.println(userId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
//        Optional<User> isExist = userRepository.findOneByLogin(login);
//        if(isExist != null)
//            System.out.println(isExist);
        User user = new User();
        user.setUser_id(userId);
        user.setLogin(login);
        user.setPasswordHash(password);
//        user.setPasswordHash(Base64.encode(password.getBytes()).toString());
//        System.out.println("user password hash = " + user.getPasswordHash());
        user.setRole(Role.USER);
        try {
            userRepository.save(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + "http://localhost:8082/register?userexist";
        }
        return "redirect:" + "http://localhost:8082/success";
    }
}
