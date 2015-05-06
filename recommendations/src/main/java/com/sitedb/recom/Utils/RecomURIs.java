package com.sitedb.recom.utils;

/**
 * Created by sketchyy on 29.04.2015.
 */
public class RecomURIs {
    // Hosts
    public static final String FRONT = "http://localhost:8082";
    public static final String DB_CONTROLLER = "http://localhost:8080";
    public static final String SESSION = "http://localhost:8085";
//    public static final String USERS = "http://localhost:8080/";

    // Sites
    public static final String ALL_SITES = DB_CONTROLLER + "/sites";
    public static final String SITES_PAGED_URI = DB_CONTROLLER + "/sites?page={page}&size={size}";
    public static final String SITE_URI = DB_CONTROLLER + "/sites/{siteId}";
    public static final String SIMILAR_SITES_URI = DB_CONTROLLER + "/sites/search/findSimilar?site={siteId}";
    public static final String SITES_BY_TAG_URI = DB_CONTROLLER + "/tags/{tagId}/sites";

    // Tags
    public static final String TAG_URI = DB_CONTROLLER + "/tags/{tagId}";
    public static final String TAGS_PAGED_URI = DB_CONTROLLER + "/tags?page={page}&size={size}";
    public static final String TAGS_BY_SITE_URI = DB_CONTROLLER + "/sites/{siteId}/tags";

    // Rating
    public static final String ALL_RATES = DB_CONTROLLER + "/rates";
    public static final String FIND_RATE_BY_SITE_AND_USER = DB_CONTROLLER + "/rates/search/findBySiteAndUser?site={siteId}&user={userId}";
    public static final String GET_VOTERS_COUNT_URI = DB_CONTROLLER + "/rates/search/getVotersCount?site={siteId}";
    public static final String GET_AVG_RATING_URI = DB_CONTROLLER + "/rates/search/getAvgRating?site={siteId}";

    // Authorization
    public static final String LOGIN_PAGE = SESSION + "/login";

}
