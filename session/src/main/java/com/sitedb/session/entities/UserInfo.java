package com.sitedb.session.entities;

/**
 * Created by Alexander on 08.05.2015.
 */

import java.util.Date;

/**
 * Created by sketchyy on 29.04.2015.
 */

public class UserInfo {

    private long id;

    private String name;

    private String surname;

    private String email;

    private Date birthday;

    private String gender;

    public UserInfo() {
    }

    public UserInfo(String name, String surname, String email, Date birthday, String gender) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
