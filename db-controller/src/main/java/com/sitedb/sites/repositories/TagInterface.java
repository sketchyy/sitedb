package com.sitedb.sites.repositories;

import com.sitedb.sites.entities.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by sketchyy on 23.04.2015.
 */


public interface TagInterface extends PagingAndSortingRepository<Tag, Long> {

}
