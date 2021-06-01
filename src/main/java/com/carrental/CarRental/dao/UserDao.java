package com.carrental.CarRental.dao;

import com.carrental.CarRental.model.User;

import java.util.Optional;

public interface UserDao {
    public Optional<User> findByLogin(String login);
}
