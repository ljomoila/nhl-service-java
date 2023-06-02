package com.ljomoila.nhl.exception;

public class NhlException extends RuntimeException {

    public NhlException() {
        super();
    }
    public NhlException(String message, Throwable cause) {
        super(message, cause);
    }
    public NhlException(String message) {
        super(message);
    }
    public NhlException(Throwable cause) {
        super(cause);
    }
}
