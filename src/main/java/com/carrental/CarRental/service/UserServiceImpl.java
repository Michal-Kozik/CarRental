package com.carrental.CarRental.service;

import com.carrental.CarRental.dao.UserDao;
import com.carrental.CarRental.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserServiceImpl implements UserService {

    @EJB
    private UserDao userDao;

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login).orElse(null);
    }

    @Override
    public boolean verify(String login, String password) {
        User user = userDao.findByLogin(login).orElse(null);
        return user != null ? password.equals(user.getPassword()) : false;
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}
