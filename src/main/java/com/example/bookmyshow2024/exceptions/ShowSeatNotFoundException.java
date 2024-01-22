package com.example.bookmyshow2024.exceptions;

public class ShowSeatNotFoundException extends RuntimeException{
    public ShowSeatNotFoundException(String Message){
        super(Message);
    }
}
