package com.sitedb.session;

import com.sitedb.session.controllers.RestTemplateCreator;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Alexander on 11.05.2015.
 */

public class TestRegisterUser extends TestCase {
    //    @RequestParam("login") String login,
//    @RequestParam("password") String password,
//    @RequestParam("name") String name,
//    @RequestParam("surname") String surname,
//    @RequestParam("email") String email,
//    @RequestParam("birthday") String birthday,
//    @RequestParam("gender")
    @Test
    public void testRegisterUser() {
        System.out.println("Test run");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("login", "testLogin");
        httpHeaders.add("password", "testPas");
        httpHeaders.add("name", "testName");
        httpHeaders.add("surname", "testSurname");
        httpHeaders.add("email", "testEmail");
        httpHeaders.add("birthday", "10/10/2010");
        httpHeaders.add("gender", "m");
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = RestTemplateCreator.create();
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8085/register", HttpMethod.POST, httpEntity, String.class);
        System.out.println(result.getBody());
        assertEquals(result.getBody(), "redirect:" + "http://localhost:8082/success");
    }

}
