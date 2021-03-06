package com.carrental.CarRental.controller;

import com.carrental.CarRental.model.Car;
import com.carrental.CarRental.service.CarService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CarController implements Serializable {
    @EJB
    private CarService carService;
    private List<Car> cars;
    private Car editedCar;

    @PostConstruct
    private void init() {
        cars = carService.findAll();
    }

    // Getters and Setters.
    public List<Car> getCars() {
        return cars;
    }

    public Car getEditedCar() {
        return editedCar;
    }

    public void setEditedCar(Car editedCar) {
        this.editedCar = editedCar;
    }

    public Car.State[] getCarStates() {
        return Car.State.values();
    }

    // CRUD Methods.
    public void onAddCar() {
        editedCar = new Car();
    }

    public void onEditCar(Car car) {
        editedCar = car;
    }

    public void onSaveCar() {
        if (editedCar.getId() == null) {
            cars.add(editedCar);
        }
        Car savedCar = carService.save(editedCar);
        cars.replaceAll(c -> c != editedCar ? c : savedCar);
        editedCar = null;
    }

    public void onRemoveCar(Car car) {
        carService.delete(car);
        cars.remove(car);
    }

    public void onCancelCar() {
        cars.replaceAll(c -> c != editedCar ? c : carService.findById(editedCar.getId()));
        editedCar = null;
    }

    //Other Methods.
    public boolean isCarAvailable(Car car) {
        if (car.getState() == Car.State.AVAILABLE) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCarBooked(Car car) {
        if (car.getState() == Car.State.BOOKED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCarOccupied(Car car) {
        if (car.getState() == Car.State.OCCUPIED) {
            return true;
        } else {
            return false;
        }
    }
}
