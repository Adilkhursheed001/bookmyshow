package com.example.bookmyshow2024.repository;

import model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Override
     Booking save(Booking booking);
}
