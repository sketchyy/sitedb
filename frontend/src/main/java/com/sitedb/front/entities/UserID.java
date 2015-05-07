package com.sitedb.front.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alexander on 05.05.2015.
 */
public class UserID {

    @JsonProperty("id")
    long id;

    @JsonCreator
    public UserID(@JsonProperty("id") long id) {
        this.id = id;
    }

    public UserID(UserID userID) {
        this.id = userID.getId();
    }

    public long getId() {
        return id;
    }

    public UserID() {
    }

    public void setId(long id) {
        this.id = id;
    }
}
