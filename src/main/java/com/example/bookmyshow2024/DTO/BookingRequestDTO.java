package com.example.bookmyshow2024.DTO;

import lombok.Getter;
import lombok.Setter;
import model.Show;
import model.ShowSeat;
import model.User;

import java.util.List;

@Getter
@Setter
public class BookingRequestDTO {

    private List<Long> showSeatsid;

    private Long userId;

    private Long showId;


}
