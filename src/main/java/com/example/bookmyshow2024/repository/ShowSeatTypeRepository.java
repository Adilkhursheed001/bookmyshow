package com.example.bookmyshow2024.repository;

import model.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Long> {

    List<ShowSeatType> findAllByShow(Long showId);
}
