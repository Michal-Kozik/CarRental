package com.carrental.CarRental.dao;

import com.carrental.CarRental.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @PersistenceContext(unitName = "PU")
    private EntityManager entityManager;

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(entityManager.find(User.class, login));
    }
}
