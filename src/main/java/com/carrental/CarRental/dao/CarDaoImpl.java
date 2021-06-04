package com.carrental.CarRental.dao;

import com.carrental.CarRental.model.Car;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CarDaoImpl implements CarDao {
    @PersistenceContext(name = "PU")
    private EntityManager entityManager;

    @Override
    public Car save(Car t) {
        if (t.getId() == null)
            entityManager.persist(t);
        t = entityManager.merge(t);
        return t;
    }

    @Override
    public void delete(Car t) {
        t = entityManager.merge(t);
        entityManager.remove(t);
    }

    @Override
    public Car findById(Long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public List<Car> findAll() {
        return entityManager.createNamedQuery("Car.findAll", Car.class).getResultList();
    }

    @Override
    public List<Car> findAvailableCars() {
        return entityManager.createNamedQuery("Car.findAvailableCars", Car.class).getResultList();
    }
}
