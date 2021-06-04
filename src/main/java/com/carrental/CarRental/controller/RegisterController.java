package com.carrental.CarRental.controller;

import com.carrental.CarRental.model.User;
import com.carrental.CarRental.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Named
@ViewScoped
public class RegisterController implements Serializable {
    @EJB
    private UserService userService;
    private User newUser;
    private boolean isLoginOrEmailInvalid;

    @PostConstruct
    private void initUser() {
        addUser();
        isLoginOrEmailInvalid = false;
    }

    // Getters and Setters.
    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public boolean isLoginOrEmailInvalid() {
        return isLoginOrEmailInvalid;
    }

    // Methods.
    public void addUser() {
        newUser = new User();
    }

    public String onRegisterUser() {
        if (userService.findByLoginOrEmail(newUser.getLogin(), newUser.getEmail()) != null) {
            isLoginOrEmailInvalid = true;
            return null;
        } else {
            newUser.addGroup("ROLE_CLIENT");
            userService.saveUser(newUser);
            newUser = null;
            return "home";
        }
    }
}
