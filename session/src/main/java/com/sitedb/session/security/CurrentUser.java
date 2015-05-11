package com.sitedb.session.security;

import com.sitedb.session.entities.Role;
import com.sitedb.session.entities.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by SaivR1t
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
