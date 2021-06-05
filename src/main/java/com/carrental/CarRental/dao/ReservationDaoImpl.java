package com.carrental.CarRental.dao;

import com.carrental.CarRental.model.Reservation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
public class ReservationDaoImpl implements ReservationDao {
    @PersistenceContext(name = "PU")
    private EntityManager entityManager;

    @Override
    public List<Reservation> findAll() {
        return entityManager.createNamedQuery("Reservation.findAll", Reservation.class).getResultList();
    }

    @Override
    public Optional<List<Reservation>> findReservationsByLogin(String login) {
        try {
            TypedQuery<Reservation> query = entityManager.createNamedQuery("Reservation.findReservationsByLogin", Reservation.class);
            query.setParameter("login", login);
            List<Reservation> result = query.getResultList();
            return Optional.of(result);
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public void saveReservation(Reservation reservation) {
        if (reservation.getId() == null)
            entityManager.persist(reservation);
        entityManager.merge(reservation);
    }
}
