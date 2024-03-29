package net.wojteksz128.authservice.service.exception;

public class ObjectNotCorrespondingException extends Exception {

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
