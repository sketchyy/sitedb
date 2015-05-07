package com.sitedb.session.repositories;

import com.sitedb.session.entities.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Alexander on 07.05.2015.
 */
public interface SessionRepository extends CrudRepository<Session, String> {
    //        @Query("SELECT r FROM Rate r WHERE r.site.id = :site AND r.user.id = :user")
    Session findBySessionId(@Param("SESSION_ID") String sessionId);

}
