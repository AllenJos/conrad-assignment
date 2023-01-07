package de.conrad.codeworkshop.factory.exception;

import java.time.LocalDateTime;

/**
 * Java Class used by {@link de.conrad.codeworkshop.factory.config.HandlerConfig} for custom Exception Handling
 */
public class ExceptionDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ExceptionDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

