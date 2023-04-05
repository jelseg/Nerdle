package Nerdle.Model;

public class NerdleException extends RuntimeException{
    public NerdleException() {
    }

    public NerdleException(String message) {
        super(message);
    }

    public NerdleException(String message, Throwable cause) {
        super(message, cause);
    }

    public NerdleException(Throwable cause) {
        super(cause);
    }
}
