package com.sitedb.sites.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sketchyy on 22.04.2015.
 */

@Entity
@Table(name = "SDB_SITES")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "site_id_generator")
    @SequenceGenerator(name = "site_id_generator", sequenceName = "SDB_SITES_SEQ", allocationSize = 1)
    @Column(name = "SITE_ID")
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "URL")
    private String url;
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "SDB_SITES_TAGS",
            joinColumns = {
                    @JoinColumn(name = "SITE_ID", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "TAG_ID", nullable = false, updatable = false
                    )})
    private Set<Tag> tags = new HashSet<>(0);

    protected Site() {
    }

    public Site(String name, String url) {
        this.name = name;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Site[id=%d, name=%s, url=%s", id, name, url);
    }
}
