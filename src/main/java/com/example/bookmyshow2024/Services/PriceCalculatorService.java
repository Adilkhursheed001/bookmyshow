package com.example.bookmyshow2024.Services;

import com.example.bookmyshow2024.repository.ShowSeatTypeRepository;
import model.Show;
import model.ShowSeat;
import model.ShowSeatType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public int calculatePrice(List<ShowSeat> showSeats, Show show){

        int amount = 0;
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show.getId());

        for(ShowSeat showSeat : showSeats){
            for(ShowSeatType showSeatType : showSeatTypes){
                 if(showSeat.getSeat().getSeattype().equals(showSeatType.getSeatType())){
                     amount = amount + showSeatType.getPrice();
                 }
            }
        }
        return amount;
    }
}
