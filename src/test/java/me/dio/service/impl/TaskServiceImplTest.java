package me.dio.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import me.dio.domain.model.Task;
import me.dio.domain.model.enums.Priority;
import me.dio.domain.model.enums.Status;
import me.dio.domain.repository.TaskRepository;
import me.dio.service.exception.TaskNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl(taskRepository);
    }

    @Test
    void createAppliesDefaultStatusAndPriority() {
        Task task = new Task();
        task.setTitle("Write tests");
        when(taskRepository.save(task)).thenReturn(task);

        Task created = taskService.create(task);

        assertEquals(Status.PENDING, created.getStatus());
        assertEquals(Priority.MEDIUM, created.getPriority());
        verify(taskRepository).save(task);
    }

    @Test
    void findByIdThrowsWhenTaskDoesNotExist() {
        when(taskRepository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.findById(42L));
    }

    @Test
    void deleteChecksTaskExistsBeforeDeleting() {
        Task task = new Task();
        task.setId(7L);
        when(taskRepository.findById(7L)).thenReturn(Optional.of(task));

        taskService.delete(7L);

        verify(taskRepository).delete(task);
    }

    @Test
    void updateChangesTaskAndRetainsOptionalStatusAndPriority() {
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Old title");
        existingTask.setStatus(Status.IN_PROGRESS);
        existingTask.setPriority(Priority.HIGH);

        Task update = new Task();
        update.setTitle("New title");
        update.setDescription("Updated details");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(existingTask);

        Task updated = taskService.update(1L, update);

        assertEquals("New title", updated.getTitle());
        assertEquals("Updated details", updated.getDescription());
        assertEquals(Status.IN_PROGRESS, updated.getStatus());
        assertEquals(Priority.HIGH, updated.getPriority());
    }

    @Test
    void completeTaskSetsCompletedStatus() {
        Task task = new Task();
        task.setId(1L);
        task.setStatus(Status.IN_PROGRESS);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        Task completed = taskService.completeTask(1L);

        assertEquals(Status.COMPLETED, completed.getStatus());
    }

    @Test
    void filtersTasksByStatusAndPriority() {
        when(taskRepository.findAllByStatusAndPriority(Status.PENDING, Priority.HIGH))
                .thenReturn(List.of());

        assertEquals(0, taskService.findAll(Status.PENDING, Priority.HIGH).size());
        verify(taskRepository).findAllByStatusAndPriority(Status.PENDING, Priority.HIGH);
    }
}
