package com.carrental.CarRental.service;

import com.carrental.CarRental.model.Car;

import java.util.List;

public interface CarService {
    Car save(Car t);
    void delete(Car t);
    Car findById(Long id);
    List<Car> findAll();
}
