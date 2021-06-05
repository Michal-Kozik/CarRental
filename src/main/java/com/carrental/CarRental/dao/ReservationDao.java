package com.carrental.CarRental.dao;

import com.carrental.CarRental.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationDao {
    public List<Reservation> findAll();
    public Optional<List<Reservation>> findReservationsByLogin(String login);
    public void saveReservation(Reservation reservation);
}
