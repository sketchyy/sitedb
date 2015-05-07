package com.sitedb.front.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sketchyy on 03.05.2015.
 */
public class Tag {
    private String name;
    private long id;
    private String hrefToFront;

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
