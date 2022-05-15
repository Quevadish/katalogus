package epam.progkor.katalogus.katalogus.model.exceptions;

public class NotFoundExceptations extends RuntimeException {

    public NotFoundExceptations(final String message) {
        super(message);
    }

    public NotFoundExceptations(final String message,final Throwable cause) {
        super(message, cause);
    }

    public NotFoundExceptations() {

    }
}
