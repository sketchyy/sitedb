package com.sitedb.dbcontroller.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sketchyy on 29.04.2015.
 */

@Entity
@Table(name = "SDB_USERS")
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="user_id_gen")
    @SequenceGenerator(name="user_id_gen", sequenceName="SDB_USERS_SEQ", allocationSize = 1)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "GENDER")
    private String gender;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "SDB_FAVOURITES",
            joinColumns = {
                    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "SITE_ID", nullable = false, updatable = false
                    )})
    private Collection<Site> favourites = new HashSet<>(0);

    public User() {
    }

    public User(String name, String surname, String email, Date birthday, String gender) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Collection<Site> getFavourites() {
        return favourites;
    }

    public void setFavourites(Collection<Site> favourites) {
        this.favourites = favourites;
    }
}
