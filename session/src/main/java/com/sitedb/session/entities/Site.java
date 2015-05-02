package com.sitedb.session.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sketchyy on 23.04.2015.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Site {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
