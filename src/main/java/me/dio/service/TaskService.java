package me.dio.service;

import java.util.List;

import me.dio.domain.model.Task;
import me.dio.domain.model.enums.Priority;
import me.dio.domain.model.enums.Status;

public interface TaskService {

    List<Task> findAll();

    List<Task> findAll(Status status, Priority priority);

    Task findById(Long id);

    Task create(Task task);

    Task update(Long id, Task task);

    void delete(Long id);

    Task completeTask(Long id);
}
