package com.sitedb.session.security;

/**
 * Created by SaivR1t
 */
public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}