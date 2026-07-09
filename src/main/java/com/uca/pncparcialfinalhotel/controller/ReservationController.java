package com.uca.pncparcialfinalhotel.controller;

import com.uca.pncparcialfinalhotel.dto.reservation.CreateReservationRequest;
import com.uca.pncparcialfinalhotel.dto.reservation.ReservationResponse;
import com.uca.pncparcialfinalhotel.security.CustomUserDetails;
import com.uca.pncparcialfinalhotel.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasRole('HUESPED')")
    public ResponseEntity<ReservationResponse> create(
            @Valid @RequestBody CreateReservationRequest request,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        return ResponseEntity.ok(reservationService.create(request, currentUser));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('HUESPED')")
    public ResponseEntity<List<ReservationResponse>> getMyReservations(
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        return ResponseEntity.ok(reservationService.getMyReservations(currentUser));
    }

    @GetMapping("/hotel")
    @PreAuthorize("hasRole('RECEPCIONISTA')")
    public ResponseEntity<List<ReservationResponse>> getReservationsByHotel(
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        return ResponseEntity.ok(reservationService.getReservationsByHotel(currentUser));
    }

    @PatchMapping("/{reservationId}/confirm")
    @PreAuthorize("hasRole('RECEPCIONISTA')")
    public ResponseEntity<ReservationResponse> confirmReservation(
            @PathVariable Integer reservationId,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        return ResponseEntity.ok(reservationService.confirmReservation(reservationId, currentUser));
    }

    @PatchMapping("/{reservationId}/cancel")
    @PreAuthorize("hasRole('HUESPED')")
    public ResponseEntity<ReservationResponse> cancelMyReservation(
            @PathVariable Integer reservationId,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        return ResponseEntity.ok(reservationService.cancelMyReservation(reservationId, currentUser));
    }
}