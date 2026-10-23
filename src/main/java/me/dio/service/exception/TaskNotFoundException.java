package me.dio.service.exception;

public class TaskNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(Long id) {
        super("Task with ID %d was not found.".formatted(id));
    }
}
