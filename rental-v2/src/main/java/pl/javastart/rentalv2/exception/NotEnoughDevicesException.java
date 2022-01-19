package pl.javastart.rentalv2.exception;

public class NotEnoughDevicesException extends RuntimeException {
    public NotEnoughDevicesException(String message) {
        super(message);
    }
}
