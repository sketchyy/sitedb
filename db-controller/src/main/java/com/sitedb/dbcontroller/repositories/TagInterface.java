package com.sitedb.dbcontroller.repositories;

import com.sitedb.dbcontroller.entities.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by sketchyy on 23.04.2015.
 */


public interface TagInterface extends PagingAndSortingRepository<Tag, Long> {

}
