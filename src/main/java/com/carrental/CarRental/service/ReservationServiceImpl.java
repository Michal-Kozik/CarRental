package com.carrental.CarRental.service;

import com.carrental.CarRental.dao.ReservationDao;
import com.carrental.CarRental.model.Reservation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class ReservationServiceImpl implements ReservationService {
    @EJB
    private ReservationDao reservationDao;

    @Override
    public List<Reservation> findAll() {
        return reservationDao.findAll();
    }

    @Override
    public Optional<List<Reservation>> findReservationsByLogin(String login) {
        return reservationDao.findReservationsByLogin(login);
    }

    @Override
    public void saveReservation(Reservation reservation) {
        reservationDao.saveReservation(reservation);
    }
}
