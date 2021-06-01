package com.carrental.CarRental.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UserGroup extends AbstractModel {
    private String name;
    @ManyToOne
    private User user;

    public UserGroup() { }

    public UserGroup(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
