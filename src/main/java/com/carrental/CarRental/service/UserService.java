package com.carrental.CarRental.service;

import com.carrental.CarRental.model.User;

import java.util.List;

public interface UserService {
    public User findByLogin(String login);
    public User findByLoginOrEmail(String login, String email);
    public boolean verify(String login, String password);
    public void saveUser(User user);
}
