package com.sitedb.front.entities;

import java.util.Date;

/**
 * Created by sketchyy on 03.05.2015.
 */

public class Comment {
    private long id;

    private String text;
    private Date time;
    private User user;
    private Site site;

    public Comment(String text, Date time) {
        this.text = text;
        this.time = time;
    }

    public Comment() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Comment[text=%s, id=%d, name=%s", text, id, user.getName());
    }
}
