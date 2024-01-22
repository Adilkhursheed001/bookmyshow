package model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseClass{

    private String name;

    private int rowVal;

    private int columnVal;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seattype;

}
