package com.carrental.CarRental.controller;

import com.carrental.CarRental.model.Car;
import com.carrental.CarRental.service.CarService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ReservationController implements Serializable {
    @EJB
    private CarService carService;
    private List<Car> cars;

    @PostConstruct
    private void init() {
        cars = carService.findAvailableCars();
    }

    //Getters and Setters.
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
