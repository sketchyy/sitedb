package com.sitedb.dbcontroller.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sketchyy on 29.04.2015.
 */

@Entity
@Table(name = "SDB_COMMENT")
public class Comment {

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="comm_id_gen")
    @SequenceGenerator(name="comm_id_gen", sequenceName="SDB_COMMENT_SEQ", allocationSize = 1)
    private long id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "COMMENT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "SITE_ID")
    private Site site;

    public Comment(String text, Date time) {
        this.text = text;
        this.time = time;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
