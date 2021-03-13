package net.debreczeni.food.delivery.exceptions;

public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException() {
        super("Invalid login credentials");
    }
}
