package com.carrental.CarRental.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c")
})
@Entity
public class Car extends AbstractModel {
    public enum State { AVAILABLE, BOOKED, OCCUPIED}

    private String brand;
    private int numberOfSeats;
    private int numberOfDoors;
    private boolean airConditioning;
    private boolean manualGearbox;
    @Column(precision = 13, scale = 2)
    private BigDecimal price;
    @Enumerated
    private State state;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new LinkedList<>();

//    public Car() { }

    public Car() {
        brand = "Unknown";
        numberOfSeats = 0;
        numberOfDoors = 0;
        airConditioning = false;
        manualGearbox = false;
        price = BigDecimal.valueOf(0);
        state = State.OCCUPIED;
    }

    // Getters and Setters.
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean isManualGearbox() {
        return manualGearbox;
    }

    public void setManualGearbox(boolean manualGearbox) {
        this.manualGearbox = manualGearbox;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    // Methods.
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setCar(this);
    }
}
