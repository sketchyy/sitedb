package com.sitedb.sites.repositories;

import com.sitedb.sites.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by sketchyy on 29.04.2015.
 */

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
