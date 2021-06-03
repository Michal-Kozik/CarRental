package com.carrental.CarRental.controller;

import com.carrental.CarRental.bean.UserBean;
import com.carrental.CarRental.service.UserService;
import com.carrental.CarRental.util.JSF;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginController implements Serializable {

    @Inject
    private UserBean userBean;
    @EJB
    private UserService userService;

    private String login;
    private String password;

    // Getters and Setters.
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Methods.
    public void onLogin() throws IOException, ServletException {
        if (userService.verify(login, password)) {
            userBean.setLogin(login);
            //
            userBean.setRoles(userService.findByLogin(login).getUserGroups());
            JSF.redirect("home.xhtml");
        } else {
            JSF.addErrorMessage("Niepoprawne dane");
        }
    }

    public void onLogout() throws IOException {
        JSF.invalidateSession();
        JSF.redirect("home.xhtml");
    }
}
