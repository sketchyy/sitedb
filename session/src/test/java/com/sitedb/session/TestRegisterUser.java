package com.sitedb.session;

import com.sitedb.session.utils.SessionURI;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Alexander on 11.05.2015.
 */
public class TestRegisterUser extends TestCase {

    @Test
    public void testRegisterUser() {
        String url = SessionURI.SessionRegister;
        String email = "testEmail5";
        String name = "testName7";
        String login = "testLog5";
        String pass = "testPas5";
        URL obj = null;
        HttpURLConnection con = null;
        int responseCode = 0;
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            String urlParameters = "login=" + login + "&password=" + pass + "&name=" + name +
                    "&surname=testSurnameq&email=" + email + "&birthday=10/10/2010&gender=m";
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);
//            if(responseCode == 200){
//                URL objDelete = new URL(SessionURI.DbControllerUsers + "/);
//                HttpURLConnection conDelete = (HttpURLConnection) objDelete.openConnection();
//                String urlParamDelete = "";
//                conDelete.setDoOutput(true);
//                DataOutputStream wr2 = new DataOutputStream(conDelete.getOutputStream());
//                wr2.writeBytes(urlParamDelete);
//                wr2.flush();
//                wr2.close();
//                int deleteCode = conDelete.getResponseCode();
//
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(responseCode, 200);
    }

}
