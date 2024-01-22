package com.example.bookmyshow2024.repository;

import model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {

    @Override
    Optional<Show> findById(Long aLong);
}
