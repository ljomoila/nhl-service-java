package com.ljomoila.nhl.exception;

import org.springframework.http.HttpStatus;

public class NhlException extends RuntimeException {
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public NhlException() {
        super();
    }
    public NhlException(String message, Throwable cause) {
        super(message, cause);
    }
    public NhlException(String message) {
        super(message);
    }
    public NhlException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);

        this.status = status;
    }
    public NhlException(String message, HttpStatus status) {
        super(message);

        this.status = status;
    }
    public NhlException(Throwable cause) {
        super(cause);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
