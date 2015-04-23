package com.sitedb.sites.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sketchyy on 23.04.2015.
 */

@Entity
@Table(name = "RS1_TAGS")
public class Tag {

    @Id
    @Column(name = "TAG_ID")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="tag_id_generator")
    @SequenceGenerator(name="tag_id_generator", sequenceName="TAGS_SEQ", allocationSize = 1)
    private long id;
    @Column(name = "NAME")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<Site> sites = new HashSet<Site>(0);

    public Tag(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Site> getSites() {
        return sites;
    }

    public void setSites(Set<Site> sites) {
        this.sites = sites;
    }

    public Tag() {
    }
}
