package com.carrental.CarRental.controller;

import com.carrental.CarRental.bean.UserBean;
import com.carrental.CarRental.model.Car;
import com.carrental.CarRental.model.Reservation;
import com.carrental.CarRental.service.CarService;
import com.carrental.CarRental.service.ReservationService;
import com.carrental.CarRental.util.JSF;
import com.carrental.CarRental.util.JavaMail;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class ReservationController implements Serializable {
    @Inject
    private UserBean userBean;
    @EJB
    private CarService carService;
    @EJB
    private ReservationService reservationService;
    private List<Car> cars;
    private List<Reservation> allReservations;
    private List<Reservation> userReservations;

    @PostConstruct
    private void init() {
        cars = carService.findAvailableCars();
        allReservations = reservationService.findAll();
        userReservations = reservationService.findReservationsByLogin(userBean.getLogin()).get();
    }

    // Getters and Setters.
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Reservation> getAllReservations() {
        return allReservations;
    }

    public void setAllReservations(List<Reservation> allReservations) {
        this.allReservations = allReservations;
    }

    public List<Reservation> getUserReservations() {
        return userReservations;
    }

    public void setUserReservations(List<Reservation> userReservations) {
        this.userReservations = userReservations;
    }

    // Methods.
    public void onReservation(Car car) throws Exception {
        Reservation reservation = new Reservation();
        reservation.setReservationFrom(new Date());
        userBean.getUser().addReservation(reservation);
        car.setState(Car.State.BOOKED);
        carService.save(car);
        car.addReservation(reservation);
        reservationService.saveReservation(reservation);
        JSF.redirect("/forClient/clientReservations.xhtml");
        // Wysylanie mejla bedzie tylko dla uzytkownika, ktoremu stworzono poczte, aby bylo mozna sprawdzic dzialanie JavaMail.
        // W domyslnej wersji aplikacji, ponizszy warunek bylby zdjety.
        if (userBean.getUser().getEmail().equals("klientklientowski123@gmail.com")) {
            JavaMail.sendMail(userBean.getUser().getEmail(), car);
        }
    }

    public void onRemoveReservation(Reservation reservation) {
        reservation.getUser().removeReservation(reservation);
        reservation.getCar().removeReservation(reservation);
        userReservations.remove(reservation);
        allReservations.remove(reservation);
        reservationService.deleteReservation(reservation);
        reservation.getCar().setState(Car.State.AVAILABLE);
        carService.save(reservation.getCar());
    }

    public void onReservationGiveCar(Reservation reservation) {
        reservation.getCar().setState(Car.State.OCCUPIED);
        carService.save(reservation.getCar());
    }

    public void onReservationGetCar(Reservation reservation) {
        reservation.getCar().setState(Car.State.AVAILABLE);
        carService.save(reservation.getCar());
        onRemoveReservation(reservation);
    }
}
