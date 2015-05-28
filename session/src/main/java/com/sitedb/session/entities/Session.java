package com.sitedb.session.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Alexander on 07.05.2015.
 */
@Entity
@Table(name = "\"UDB_SESSIONS\"")
public class Session {

    @Id
    @Column(name = "\"SESSION_ID\"")
    String sessionId;

    @Column(name = "\"USER_ID\"")
    // TODO many to one?
            Long userId;

    @Column(name = "\"EXPIRES\"")
    Timestamp expires;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getExpires() {
        return expires;
    }

    public void setExpires(Timestamp expires) {
        this.expires = expires;
    }
}
