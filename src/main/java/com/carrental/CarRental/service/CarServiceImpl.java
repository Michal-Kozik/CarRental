package com.carrental.CarRental.service;

import com.carrental.CarRental.dao.CarDao;
import com.carrental.CarRental.model.Car;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CarServiceImpl implements CarService {
    @EJB
    private CarDao carDao;

    @Override
    public Car save(Car t) {
        return carDao.save(t);
    }

    @Override
    public void delete(Car t) {
        carDao.delete(t);
    }

    @Override
    public Car findById(Long id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public List<Car> findAvailableCars() {
        return carDao.findAvailableCars();
    }
}
