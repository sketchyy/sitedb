package com.sitedb.dbcontroller.repositories;

import com.sitedb.dbcontroller.entities.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sketchyy on 29.04.2015.
 */

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
