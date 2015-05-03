package com.sitedb.dbcontroller.repositories;

import com.sitedb.dbcontroller.entities.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sketchyy on 22.04.2015.
 */

//@RepositoryRestResource(collectionResourceRel = "com.sitedb.dbcontroller", path = "com.sitedb.dbcontroller")
public interface SiteRepository extends PagingAndSortingRepository<Site, Long> {
    List<Site> findByName(@Param("name") String name);
}
