package net.wojteksz128.authservice.exception;

@SuppressWarnings("unused")
public class EmptyResultException extends Exception {
    public EmptyResultException() {
        super();
    }

    public EmptyResultException(String message) {
        super(message);
    }

    public EmptyResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyResultException(Throwable cause) {
        super(cause);
    }

    protected EmptyResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
