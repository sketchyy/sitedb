package com.sitedb.dbcontroller.repositories;

import com.sitedb.dbcontroller.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by sketchyy on 29.04.2015.
 */

public interface UserRepository extends CrudRepository<User, Long> {

    String IS_SITE_IN_FAVOURITES_QUERY = "SELECT COUNT(s.id) " +
                                            "FROM User u JOIN u.favourites s " +
                                            "WHERE s.id = :site AND u.id = :user";

    @Query(IS_SITE_IN_FAVOURITES_QUERY)
    Integer isSiteInFavourites(@Param("user") Long user, @Param("site") Long site);

}
