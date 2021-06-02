package com.carrental.CarRental.controller;

import com.carrental.CarRental.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RegisterController implements Serializable {
    @EJB
    private UserService userService;
}
