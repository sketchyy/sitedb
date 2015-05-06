package com.sitedb.front;

/**
 * Created by sketchyy on 29.04.2015.
 */
public class FrontURIs {
    // Hosts
    public static final String FRONT = "http://localhost:8082";
    public static final String RECOM = "http://localhost:8084";
    public static final String DB_CONTROLLER = "http://localhost:8080";
    public static final String SESSION = "http://localhost:8085";
//    public static final String USERS = "http://localhost:8080/";

    // Sites
    public static final String ALL_SITES_PAGED = RECOM + "/sites?page={page}&size={size}";
    public static final String SITE = RECOM + "/sites/{siteId}";
    public static final String SIMILAR_SITES_URI = RECOM + "/similar?site={siteId}";
    public static final String SITES_BY_TAG_URI = RECOM + "/tags/{tagId}/sites";
    public static final String ALL_SITES = DB_CONTROLLER + "/sites";

    // Tags
    public static final String TAG_URI = RECOM + "/tags/{tagId}";
    public static final String TAGS_PAGED_URI = RECOM + "/tags?page={page}&size={size}";
    public static final String TAGS_BY_SITE_URI = RECOM + "/sites/{siteId}/tags";

    // Comments
    public static final String ALL_COMMENTS = DB_CONTROLLER + "/comments";

    // Rating
    public static final String ALL_RATES = DB_CONTROLLER + "/rates";
    public static final String FIND_RATE_BY_SITE_AND_USER =
            DB_CONTROLLER + "/rates/search/findBySiteAndUser?site={siteId}&user={userId}";
    public static final String GET_VOTERS_COUNT_URI = DB_CONTROLLER + "/rates/search/getVotersCount?site={siteId}";
    public static final String GET_AVG_RATING_URI = DB_CONTROLLER + "/rates/search/getAvgRating?site={siteId}";

    // Favourites
    public static final String ALL_FAVOURITES_URI = DB_CONTROLLER + "/users/{userId}/favourites";
    public static final String FAVOURITE_URI = DB_CONTROLLER + "/users/{userId}/favourites/{favId}";
    public static final String IS_SITE_IN_FAVS_URI = DB_CONTROLLER + "/users/search/isSiteInFavourites?site={siteId}&user={userId}";


    // Authorization
    public static final String LOGIN_PAGE = SESSION + "/login";

}
