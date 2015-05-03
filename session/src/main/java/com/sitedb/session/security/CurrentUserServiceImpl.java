package com.sitedb.session.security;

import org.springframework.stereotype.Service;

/**
 * Created by SaivR1t
 */
@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getUser_Id().equals(userId));
    }

}
