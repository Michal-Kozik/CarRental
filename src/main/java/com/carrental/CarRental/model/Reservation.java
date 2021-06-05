package com.carrental.CarRental.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
        @NamedQuery(name = "Reservation.findReservationsByLogin", query = "SELECT r FROM Reservation r WHERE r.user.login=:login")
})
@Entity
public class Reservation extends AbstractModel {
    @Temporal(TemporalType.DATE)
    private Date reservationFrom;
    @Temporal(TemporalType.DATE)
    private Date reservationTo;
    @ManyToOne
    @NotNull
    private Car car;
    @ManyToOne
    @NotNull
    private User user;

    // Getters and Setters.
    public Date getReservationFrom() {
        return reservationFrom;
    }

    public void setReservationFrom(Date reservationFrom) {
        this.reservationFrom = reservationFrom;
    }

    public Date getReservationTo() {
        return reservationTo;
    }

    public void setReservationTo(Date reservationTo) {
        this.reservationTo = reservationTo;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
