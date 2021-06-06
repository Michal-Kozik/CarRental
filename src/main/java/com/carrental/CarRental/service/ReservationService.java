package com.carrental.CarRental.service;

import com.carrental.CarRental.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findAll();
    Optional<List<Reservation>> findReservationsByLogin(String login);
    void saveReservation(Reservation reservation);
    void deleteReservation(Reservation reservation);
}
