package com.uca.pncparcialfinalhotel.repository;

import com.uca.pncparcialfinalhotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByGuestId(Integer guestId);
    List<Reservation> findByRoomHotelId(Integer hotelId);
}