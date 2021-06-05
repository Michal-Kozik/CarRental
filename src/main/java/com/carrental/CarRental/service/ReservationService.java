package com.carrental.CarRental.service;

import com.carrental.CarRental.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    public List<Reservation> findAll();
    public Optional<List<Reservation>> findReservationsByLogin(String login);
    public void saveReservation(Reservation reservation);
}
