package com.uca.pncparcialfinalhotel.service;

import com.uca.pncparcialfinalhotel.dto.reservation.CreateReservationRequest;
import com.uca.pncparcialfinalhotel.dto.reservation.ReservationResponse;
import com.uca.pncparcialfinalhotel.entity.Reservation;
import com.uca.pncparcialfinalhotel.entity.Room;
import com.uca.pncparcialfinalhotel.entity.User;
import com.uca.pncparcialfinalhotel.enums.ReservationStatus;
import com.uca.pncparcialfinalhotel.repository.ReservationRepository;
import com.uca.pncparcialfinalhotel.repository.RoomRepository;
import com.uca.pncparcialfinalhotel.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationResponse create(CreateReservationRequest request, CustomUserDetails currentUser) {
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        User guest = currentUser.getUser();

        Reservation reservation = Reservation.builder()
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .status(ReservationStatus.PENDIENTE)
                .room(room)
                .guest(guest)
                .build();

        return toResponse(reservationRepository.save(reservation));
    }

    public List<ReservationResponse> getMyReservations(CustomUserDetails currentUser) {
        return reservationRepository.findByGuestId(currentUser.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ReservationResponse> getReservationsByHotel(CustomUserDetails currentUser) {
        return reservationRepository.findByRoomHotelId(currentUser.getHotelId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ReservationResponse confirmReservation(Integer reservationId, CustomUserDetails currentUser) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        Integer reservationHotelId = reservation.getRoom().getHotel().getId();

        if (!reservationHotelId.equals(currentUser.getHotelId())) {
            throw new RuntimeException("No puedes confirmar reservas de otra sucursal");
        }

        reservation.setStatus(ReservationStatus.CONFIRMADA);
        return toResponse(reservationRepository.save(reservation));
    }

    public ReservationResponse cancelMyReservation(Integer reservationId, CustomUserDetails currentUser) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (!reservation.getGuest().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No puedes cancelar reservas que no son tuyas");
        }

        reservation.setStatus(ReservationStatus.CANCELADA);
        return toResponse(reservationRepository.save(reservation));
    }

    private ReservationResponse toResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .checkInDate(reservation.getCheckInDate())
                .checkOutDate(reservation.getCheckOutDate())
                .status(reservation.getStatus())
                .roomId(reservation.getRoom().getId())
                .roomNumber(reservation.getRoom().getRoomNumber())
                .hotelId(reservation.getRoom().getHotel().getId())
                .hotelName(reservation.getRoom().getHotel().getName())
                .guestId(reservation.getGuest().getId())
                .guestName(reservation.getGuest().getFullName())
                .build();
    }
}