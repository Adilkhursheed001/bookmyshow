package model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseClass{

    private String username;

    private String email;

    @OneToMany
    private List<Booking> bookings;
}