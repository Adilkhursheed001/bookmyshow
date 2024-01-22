package com.example.bookmyshow2024.DTO;

import lombok.Getter;
import lombok.Setter;
import model.Booking;
import model.ResponseStatus;

@Getter
@Setter
public class BookingResponseDTO {

    private Long bookingId;

    private int amount;

    private ResponseStatus responseStatus;


}
