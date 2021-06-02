package com.carrental.CarRental.dao;

import com.carrental.CarRental.model.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Optional;

@Stateless
public class UserDaoImpl implements UserDao {
    @PersistenceContext(unitName = "PU")
    private EntityManager entityManager;

    @Override
    public Optional<User> findByLogin(String login) {
//        return Optional.ofNullable(entityManager.find(User.class, login));
        try {
            TypedQuery<User> query = entityManager.createNamedQuery("User.findByLogin", User.class);
            query.setParameter("login", login);
            User result = query.getSingleResult();
            return Optional.of(result);
        } catch (NoResultException nre) {
            return Optional.empty();
        }

    }
}
