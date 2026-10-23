package me.dio.controller.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import me.dio.domain.model.Task;
import me.dio.domain.model.enums.Priority;
import me.dio.domain.model.enums.Status;

@Schema(description = "Task representation returned by the API")
public record TaskResponse(
        Long id,
        String title,
        String description,
        Status status,
        Priority priority,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public TaskResponse(Task task) {
        this(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getCreatedAt(),
                task.getUpdatedAt());
    }
}
