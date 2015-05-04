package com.sitedb.recom.entities;

/**
 * Created by sketchyy on 04.05.2015.
 */
public class Rate {
    private long id;
    private int rate;


    public Rate() {
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdByHref(String href) {
        String[] ss = href.split("/");
        id = Integer.valueOf(ss[ss.length - 1]);
    }
}
