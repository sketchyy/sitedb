package com.sitedb.session.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);

    User findByLoginAndPasswordHash(String login, String PasswordHash);
}