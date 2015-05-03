package com.sitedb.dbcontroller.repositories;

import com.sitedb.dbcontroller.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sketchyy on 29.04.2015.
 */

public interface UserRepository extends CrudRepository<User, Long> {
}
