package com.uca.pncparcialfinalhotel.dto.reservation;

import com.uca.pncparcialfinalhotel.enums.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservationResponse {

    private Integer id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status;
    private Integer roomId;
    private String roomNumber;
    private Integer hotelId;
    private String hotelName;
    private Integer guestId;
    private String guestName;
}