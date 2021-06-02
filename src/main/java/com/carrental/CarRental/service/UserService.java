package com.carrental.CarRental.service;

import com.carrental.CarRental.model.User;

public interface UserService {
    public User findByLogin(String login);
    public boolean verify(String login, String password);
    public void addUser(User user);
}
