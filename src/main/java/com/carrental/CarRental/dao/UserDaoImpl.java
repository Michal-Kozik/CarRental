package com.carrental.CarRental.dao;

import com.carrental.CarRental.model.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;
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
    public Optional<User> findByLoginOrEmail(String login, String email) {
        try {
            TypedQuery<User> query = entityManager.createNamedQuery("User.findByLoginOrEmail", User.class);
            query.setParameter("login", login);
            query.setParameter("email", email);
            User result = query.getSingleResult();
            return Optional.of(result);
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == null)
            entityManager.persist(user);
        entityManager.merge(user);
    }
}
