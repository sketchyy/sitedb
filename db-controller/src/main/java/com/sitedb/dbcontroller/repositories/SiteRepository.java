package com.sitedb.dbcontroller.repositories;

import com.sitedb.dbcontroller.entities.Site;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sketchyy on 22.04.2015.
 */

//@RepositoryRestResource(collectionResourceRel = "com.sitedb.dbcontroller", path = "com.sitedb.dbcontroller")
public interface SiteRepository extends PagingAndSortingRepository<Site, Long> {
    String FIND_SIMILAR_SITES_QUERY =
            "SELECT\n" +
                    "  DISTINCT sites.*\n" +
                    "FROM\n" +
                    "  \"SDB_SITES\" sites,\n" +
                    "  \"SDB_SITES_TAGS\" st,\n" +
                    "  (\n" +
                    "    SELECT \"TAG_ID\"\n" +
                    "    FROM \"SDB_SITES_TAGS\"\n" +
                    "    WHERE \"SITE_ID\"= :site\n" +
                    "  ) input\n" +
                    "WHERE\n" +
                    "  sites. \"SITE_ID\" = st. \"SITE_ID\"\n" +
                    "  AND st. \"TAG_ID\" = input. \"TAG_ID\"\n"; /*+*/
                    /*"ORDER BY (SELECT avg(r. \"RATE\") FROM \"SDB_RATES\" r WHERE sites. \"SITE_ID\" = r. \"SITE_ID\")"*/;

    @Query(value = FIND_SIMILAR_SITES_QUERY, nativeQuery = true)
    List<Site> findSimilar(@Param("site") Long site);

    List<Site> findByName(@Param("name") String name);
}
