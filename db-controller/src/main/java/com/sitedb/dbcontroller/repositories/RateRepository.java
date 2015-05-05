package com.sitedb.dbcontroller.repositories;

import com.sitedb.dbcontroller.entities.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by sketchyy on 29.04.2015.
 */
public interface RateRepository extends CrudRepository<Rate, Long> {
    String FIND_RATING_BY_SITE_AND_USER = "SELECT r " +
                                            "FROM Rate r " +
                                            "WHERE r.site.id = :site AND r.user.id = :user";

    String GET_AVERAGE_RATING = "SELECT AVG(r.rate)" +
                                "FROM Rate r " +
                                "WHERE r.site.id = :site";

    String GET_RATING_COUNT = "SELECT COUNT(r.rate) " +
                                "FROM Rate r " +
                                "WHERE r.site.id = :site";

    @Query(FIND_RATING_BY_SITE_AND_USER)
    Rate findBySiteAndUser(@Param("site") Long site, @Param("user") Long user);

    @Query(GET_AVERAGE_RATING)
    Double getAvgRating(@Param("site") Long site);

    @Query(GET_RATING_COUNT)
    Integer getVotersCount(@Param("site") Long site);
}

