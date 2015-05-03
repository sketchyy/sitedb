package com.sitedb.session.security;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by Alexander on 01.05.2015.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getLogin(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getUser_Id() {
        return user.getUser_id();
    }

    public Role getRole() {
        return user.getRole();
    }

}
