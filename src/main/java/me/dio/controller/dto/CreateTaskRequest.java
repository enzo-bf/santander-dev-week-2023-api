package me.dio.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import me.dio.domain.model.Task;
import me.dio.domain.model.enums.Priority;
import me.dio.domain.model.enums.Status;

@Schema(description = "Data required to create a task")
public record CreateTaskRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title must not exceed 100 characters")
        @Schema(example = "Prepare project proposal", requiredMode = Schema.RequiredMode.REQUIRED)
        String title,

        @Size(max = 500, message = "Description must not exceed 500 characters")
        @Schema(example = "Draft the scope and estimate for the next project")
        String description,

        @Schema(example = "PENDING", defaultValue = "PENDING")
        Status status,

        @Schema(example = "MEDIUM", defaultValue = "MEDIUM")
        Priority priority) {

    public Task toModel() {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setPriority(priority);
        return task;
    }
}
