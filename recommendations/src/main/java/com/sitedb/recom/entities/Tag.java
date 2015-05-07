package com.sitedb.recom.entities;

/**
 * Created by sketchyy on 03.05.2015.
 */
public class Tag {
    public static final String LINK_TO_FRONT = "http://localhost:8082/tag?id=%d";

    private String name;

    private long id;
    private String hrefToFront;

    public void setIdByLink(String link) {
        String[] ss = link.split("/");
        id = Integer.valueOf(ss[ss.length - 1]);
        hrefToFront = String.format(LINK_TO_FRONT, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Tag() {
    }
}
