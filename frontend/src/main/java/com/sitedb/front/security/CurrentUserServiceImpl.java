package com.sitedb.front.security;

import org.springframework.stereotype.Service;

/**
 * Created by Alexander on 01.05.2015.
 */
@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getUser_Id().equals(userId));
    }

}
