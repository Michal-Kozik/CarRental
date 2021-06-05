package com.carrental.CarRental.bean;

import com.carrental.CarRental.model.User;
import com.carrental.CarRental.model.UserGroup;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserBean implements Serializable {
    private String login;
    private User user;

    public boolean isLogged() {
        return login != null;
    }

    // Getters and Setters.
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
