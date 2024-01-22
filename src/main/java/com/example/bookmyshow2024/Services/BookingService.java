package com.example.bookmyshow2024.Services;

import com.example.bookmyshow2024.exceptions.ShowNotFoundException;
import com.example.bookmyshow2024.exceptions.ShowSeatNotFoundException;
import com.example.bookmyshow2024.exceptions.UserNotFoundException;
import com.example.bookmyshow2024.repository.BookingRepository;
import com.example.bookmyshow2024.repository.ShowRepository;
import com.example.bookmyshow2024.repository.ShowSeatRepository;
import com.example.bookmyshow2024.repository.UserRepository;
import model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private PriceCalculatorService priceCalculatorService;
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository,
                          PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userID, Long showId, List<Long> showSeatIds) {

        Optional<User> optionalUser = userRepository.findById(userID);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User bookerUser = optionalUser.get();

        Optional<Show> optionalShow = showRepository.findById(showId);
        if (optionalShow.isEmpty()) {
            throw new ShowNotFoundException("Show not found");
        }

        Show bookerShow = optionalShow.get();

        List<ShowSeat> bookedSeats = showSeatRepository.findAllById(showSeatIds);

        for (ShowSeat bookedSeat : bookedSeats) {
            if (!bookedSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotFoundException("ShowSeat with id" + bookedSeat.getId() + "not found.");
            }
        }

        for (ShowSeat bookedSeat : bookedSeats) {
            bookedSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatRepository.save(bookedSeat);
        }

        Booking booking = new Booking();
        booking.setUser(bookerUser);
        booking.setBookingStatus(BookingStatus.IN_PROGRESS);
        booking.setShowSeats(bookedSeats);
        booking.setPayments(new ArrayList<>());
        booking.setCretaedAt(new Date());
        booking.setLastModefiedAt(new Date());
        booking.setAmount(priceCalculatorService.calculatePrice(bookedSeats,bookerShow));

        Booking Booked = bookingRepository.save(booking);

        return Booked;

    }
}