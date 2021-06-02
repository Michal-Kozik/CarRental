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
        try {
            TypedQuery<User> query = entityManager.createNamedQuery("User.findByLogin", User.class);
            query.setParameter("login", login);
            User result = query.getSingleResult();
            return Optional.of(result);
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == null)
            entityManager.persist(user);
        entityManager.merge(user);
    }
}
