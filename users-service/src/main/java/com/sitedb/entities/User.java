package com.sitedb.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sketchyy on 03.05.2015.
 */
public class User {
    public static final String LINK_TO_FRONT = "http://localhost:8082/user?id=%d";

    private String name;
    private String surname;
    private String email;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date birthday;
    private String gender;
    private Set<Site> favourite = new HashSet<>(0);

    private long id;
    private String hrefToFront;

    public void setHrefToFront(String link) {
        String[] ss = link.split("/");
        id = Integer.valueOf(ss[ss.length - 1]);
        hrefToFront = String.format(LINK_TO_FRONT, id);
    }

    public User() {
    }

    public User(String name, String surname, String email, Date birthday, String gender) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
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

    public Set<Site> getFavourite() {
        return favourite;
    }

    public void setFavourite(Set<Site> favourite) {
        this.favourite = favourite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHrefToFront() {
        return hrefToFront;
    }

    @Override
    public String toString() {
        return String.format("user=[name=%s, surname=%s", name, surname);
    }

}
