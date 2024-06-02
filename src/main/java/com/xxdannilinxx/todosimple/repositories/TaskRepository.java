package com.xxdannilinxx.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xxdannilinxx.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long id);

    // Query 1
    // Optional<Task> findByTask_Id(Long id);

    // Query 2
    // @Query(value = "SELECT t FROM Task t WHERE t.id = :id")
    // List<Task> findByTask_Id(@Param("id") Long id);

    // Query 3
    @Query(nativeQuery = true, value = "SELECT * FROM task WHERE id = :id")
    List<Task> findByTask_Id(@Param("id") Long id);

    // Query 4
    // @PreRemove
}
