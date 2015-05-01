package com.sitedb.front.security;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Alexander on 01.05.2015.
 */

public interface UserService {
    Optional<User> getUserByUser_Id(long user_id);

    Optional<User> getUserByLogin(String login);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}
