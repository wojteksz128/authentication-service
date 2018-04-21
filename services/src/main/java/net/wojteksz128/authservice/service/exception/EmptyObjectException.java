package net.wojteksz128.authservice.service.exception;

@SuppressWarnings("unused")
public class EmptyObjectException extends Exception {
    public EmptyObjectException() {
        super();
    }

    public EmptyObjectException(String s) {
        super(s);
    }

    public EmptyObjectException(Throwable cause) {
        super(cause);
    }

    public EmptyObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    protected EmptyObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
