package me.dio.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.domain.model.Task;
import me.dio.domain.model.enums.Priority;
import me.dio.domain.model.enums.Status;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByStatus(Status status);

    List<Task> findAllByPriority(Priority priority);

    List<Task> findAllByStatusAndPriority(Status status, Priority priority);
}
