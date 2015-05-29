package com.sitedb.session.utils;

/**
 * Created by Alexander on 29.05.2015.
 */
public class SessionURI {

    public final static String DbControllerFindByEmail = "http://localhost:8080/users/search/findByEmail?email={email}";
    public final static String DbControllerUsers = "http://localhost:8080/users";
    public final static String FrontBadBirthday = "http://localhost:8082/register?baddate";
    public final static String FrontUserExist = "http://localhost:8082/register?userexist";
    public final static String FrontSuccessReg = "http://localhost:8082/success";
    public final static String SessionRegister = "http://localhost:8085/register";


}
