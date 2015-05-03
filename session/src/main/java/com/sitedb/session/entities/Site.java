package com.sitedb.session.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by SaivR1t
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
