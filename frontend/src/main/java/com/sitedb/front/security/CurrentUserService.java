package com.sitedb.front.security;

/**
 * Created by Alexander on 01.05.2015.
 */
public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}