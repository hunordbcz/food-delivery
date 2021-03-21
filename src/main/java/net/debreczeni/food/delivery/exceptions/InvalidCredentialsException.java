package net.debreczeni.food.delivery.exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException() {
        super("Invalid login credentials");
    }
}
