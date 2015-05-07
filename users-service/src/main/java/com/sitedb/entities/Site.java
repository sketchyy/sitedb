package com.sitedb.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sketchyy on 23.04.2015.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Site {
    public static final String LINK_TO_FRONT = "http://localhost:8082/site?id=%d";

    private String name;
    private String url;
    private String description;

    private long id;
    private String hrefToFront;

    public void setIdByLink(String link) {
        String[] ss = link.split("/");
        id = Integer.valueOf(ss[ss.length - 1]);
        hrefToFront = String.format(LINK_TO_FRONT, id);
    }

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

    public String getHrefToFront() {
        return hrefToFront;
    }

    public void setHrefToFront(String hrefToFront) {
        this.hrefToFront = hrefToFront;
    }


    @Override
    public String toString() {
        return String.format("Site[name=%s, url=%s", name, url);
    }
}
