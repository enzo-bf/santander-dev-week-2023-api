package me.dio.service.exception;

public class InvalidTaskException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidTaskException(String message) {
        super(message);
    }
}
