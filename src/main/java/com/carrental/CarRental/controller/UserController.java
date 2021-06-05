package com.carrental.CarRental.controller;

import com.carrental.CarRental.model.User;
import com.carrental.CarRental.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserController implements Serializable {
    @EJB
    private UserService userService;
    private List<User> users;

    @PostConstruct
    private void init() {
        users = userService.findAll();
    }

    // Getters and Setters.
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
