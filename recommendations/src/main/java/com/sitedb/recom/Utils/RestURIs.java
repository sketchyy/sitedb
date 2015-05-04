package com.sitedb.recom.utils;

/**
 * Created by sketchyy on 29.04.2015.
 */
public class RestURIs {
    public static final String ALL_SITES = "http://localhost:8080/sites";
    public static final String ALL_SITES_PAGED = "http://localhost:8080/sites?page={page}&size={size}";
    public static final String SITE = "http://localhost:8080/sites/{siteId}";

    public static final String ALL_COMMENTS = "http://localhost:8080/comments";
    public static final String ALL_RATES = "http://localhost:8080/rates";
    public static final String FIND_RATE_BY_SITE_AND_USER =
            "http://localhost:8080/rates/search/findBySiteAndUser?site={siteId}&user={userId}";

    public static final String LOGIN_PAGE = "http://localhost:8085/login";

}
