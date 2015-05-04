package com.sitedb.dbcontroller.entities;

import javax.persistence.*;

/**
 * Created by sketchyy on 29.04.2015.
 */

@Entity
@Table(name = "SDB_RATES")
public class Rate {

    @Id
    @Column(name = "RATE_ID")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="rate_id_seq")
    @SequenceGenerator(name="rate_id_seq", sequenceName="SDB_RATES_SEQ", allocationSize = 1)
    private long id;

    @Column(name = "RATE")
    private int rate;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "SITE_ID")
    private Site site;

    public Rate() {
    }

    public Rate(int rate, User user, Site site) {
        this.rate = rate;
        this.user = user;
        this.site = site;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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
