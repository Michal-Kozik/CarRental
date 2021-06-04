package com.carrental.CarRental.bean;

import com.carrental.CarRental.model.UserGroup;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserBean implements Serializable {
    private String login;
    private List<UserGroup> roles;

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

    public List<UserGroup> getRoles() {
        return roles;
    }

    public void setRoles(List<UserGroup> roles) {
        this.roles = roles;
    }

    // Methods
    public boolean hasAdminRights() {
        for (UserGroup userGroup : roles) {
            if (userGroup.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
