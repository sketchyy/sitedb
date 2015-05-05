package com.sitedb.front;

/**
 * Created by sketchyy on 29.04.2015.
 */
public class RestURIs {
    // Sites
    public static final String ALL_SITES = "http://localhost:8080/sites";
    public static final String ALL_SITES_PAGED = "http://localhost:8080/sites?page={page}&size={size}";
    public static final String SITE = "http://localhost:8080/sites/{siteId}";
    public static final String SIMILAR_SITES_URI = "http://localhost:8084/similar?site={siteId}";

    // Comments
    public static final String ALL_COMMENTS = "http://localhost:8080/comments";

    // Rating
    public static final String ALL_RATES = "http://localhost:8080/rates";
    public static final String FIND_RATE_BY_SITE_AND_USER =
            "http://localhost:8080/rates/search/findBySiteAndUser?site={siteId}&user={userId}";
    public static final String GET_VOTERS_COUNT_URI = "http://localhost:8080/rates/search/getVotersCount?site={siteId}";
    public static final String GET_AVG_RATING_URI = "http://localhost:8080/rates/search/getAvgRating?site={siteId}";

    // Favourites
    public static final String ALL_FAVOURITES_URI = "http://localhost:8080/users/{userId}/favourites";
    public static final String FAVOURITE_URI = "http://localhost:8080/users/{userId}/favourites/{favId}";
    public static final String IS_SITE_IN_FAVS_URI = "http://localhost:8080/users/search/isSiteInFavourites?site={siteId}&user={userId}";

    // Authorization
    public static final String LOGIN_PAGE = "http://localhost:8085/login";

}
