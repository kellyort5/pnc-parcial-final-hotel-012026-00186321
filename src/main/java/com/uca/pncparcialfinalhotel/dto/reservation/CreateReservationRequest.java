package com.uca.pncparcialfinalhotel.dto.reservation;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateReservationRequest {

    @NotNull
    private Integer roomId;

    @NotNull
    @FutureOrPresent
    private LocalDate checkInDate;

    @NotNull
    @FutureOrPresent
    private LocalDate checkOutDate;
}