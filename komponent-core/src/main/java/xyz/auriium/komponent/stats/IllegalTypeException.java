package xyz.auriium.komponent.stats;

public class IllegalTypeException extends IllegalStateException {

    public IllegalTypeException() {
    }

    public IllegalTypeException(String s) {
        super(s);
    }

    public IllegalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTypeException(Throwable cause) {
        super(cause);
    }
}
