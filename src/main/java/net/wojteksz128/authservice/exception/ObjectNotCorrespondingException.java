package net.wojteksz128.authservice.exception;

public class ObjectNotCorrespondingException extends Throwable {

    public ObjectNotCorrespondingException() {
        super();
    }

    public ObjectNotCorrespondingException(String message) {
        super(message);
    }

    public ObjectNotCorrespondingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotCorrespondingException(Throwable cause) {
        super(cause);
    }

    public ObjectNotCorrespondingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
