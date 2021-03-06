package com.carrental.CarRental.dao;

import com.carrental.CarRental.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    public Optional<User> findByLogin(String login);
    public Optional<User> findByLoginOrEmail(String login, String email);
    public List<User> findAll();
    public void saveUser(User user);
}
