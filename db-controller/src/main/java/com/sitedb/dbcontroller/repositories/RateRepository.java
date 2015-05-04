package com.sitedb.dbcontroller.repositories;

import com.sitedb.dbcontroller.entities.Rate;
import com.sitedb.dbcontroller.entities.Site;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sketchyy on 29.04.2015.
 */
public interface RateRepository extends CrudRepository<Rate, Long> {

    @Query("SELECT r FROM Rate r WHERE r.site.id = :site AND r.user.id = :user")
    Rate findBySiteAndUser(@Param("site") Long site, @Param("user") Long user);

}

