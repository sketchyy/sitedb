package com.sitedb.front.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sketchyy on 23.04.2015.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Site {
    public static final String linkToFront = "http://localhost:8082/sites?id=%d";

    private String name;
    private String url;
    private String description;

    private long id;
    private String frontLink;

    protected Site() {
    }

    public Site(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrontLink() {
        return frontLink;
    }

    public void setFrontLink(String frontLink) {
        this.frontLink = frontLink;
    }

    public void setIdByLink(String link) {
        String[] ss = link.split("/");
        id = Integer.valueOf(ss[ss.length - 1]);
        System.out.println("id = " + id);
        link = String.format(linkToFront, id);
        System.out.println("link = " + link);
    }

    @Override
    public String toString() {
        return String.format("Site[name=%s, url=%s", name, url);
    }
}
