package com.carrental.CarRental.dao;

import com.carrental.CarRental.model.Car;

import java.util.List;

public interface CarDao {
    Car save(Car t);
    void delete(Car t);
    Car findById(Long id);
    List<Car> findAll();
    List<Car> findAvailableCars();
}
