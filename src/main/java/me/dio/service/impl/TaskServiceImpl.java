package me.dio.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dio.domain.model.Task;
import me.dio.domain.model.enums.Priority;
import me.dio.domain.model.enums.Status;
import me.dio.domain.repository.TaskRepository;
import me.dio.service.TaskService;
import me.dio.service.exception.InvalidTaskException;
import me.dio.service.exception.TaskNotFoundException;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAll(Status status, Priority priority) {
        if (status != null && priority != null) {
            return taskRepository.findAllByStatusAndPriority(status, priority);
        }
        if (status != null) {
            return taskRepository.findAllByStatus(status);
        }
        if (priority != null) {
            return taskRepository.findAllByPriority(priority);
        }
        return findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    @Transactional
    public Task create(Task task) {
        if (task == null) {
            throw new InvalidTaskException("Task to create must not be null.");
        }
        if (task.getStatus() == null) {
            task.setStatus(Status.PENDING);
        }
        if (task.getPriority() == null) {
            task.setPriority(Priority.MEDIUM);
        }
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task update(Long id, Task task) {
        if (id == null || task == null) {
            throw new InvalidTaskException("Task ID and update data must not be null.");
        }
        Task existingTask = findById(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        if (task.getStatus() != null) {
            existingTask.setStatus(task.getStatus());
        }
        if (task.getPriority() != null) {
            existingTask.setPriority(task.getPriority());
        }
        return taskRepository.save(existingTask);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }

    @Override
    @Transactional
    public Task completeTask(Long id) {
        Task task = findById(id);
        task.setStatus(Status.COMPLETED);
        return taskRepository.save(task);
    }
}
