package com.example.bookmyshow2024.controller;

import com.example.bookmyshow2024.DTO.BookingRequestDTO;
import com.example.bookmyshow2024.DTO.BookingResponseDTO;
import com.example.bookmyshow2024.Services.BookingService;
import model.Booking;
import model.ResponseStatus;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingResponseDTO bookingResponseDTO;
    private BookingService bookingService;


    public BookingResponseDTO bookMovie(BookingRequestDTO bookingRequestDTO){
        bookingResponseDTO = new BookingResponseDTO();
        try{
       Booking  booking = bookingService.bookMovie(bookingRequestDTO.getUserId(),
                             bookingRequestDTO.getShowId(),bookingRequestDTO.getShowSeatsid());

            bookingResponseDTO.setBookingId(booking.getId());
            bookingResponseDTO.setResponseStatus(ResponseStatus.CONFIRMED);
            bookingResponseDTO.setAmount(booking.getAmount());
        }
        catch (RuntimeException runtimeException){
            bookingResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return bookingResponseDTO;

    }

    public Booking cancelMovie(){
        return null;
    }
}
