package com.targsoft.task.exception;

public class DateConvertException extends Exception {
    public DateConvertException() {
    }

    public DateConvertException(String message) {
        super(message);
    }

    public DateConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
