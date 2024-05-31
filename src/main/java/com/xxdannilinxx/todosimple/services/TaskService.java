package com.xxdannilinxx.todosimple.services;

import java.util.Optional;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxdannilinxx.todosimple.models.Task;
import com.xxdannilinxx.todosimple.models.User;
import com.xxdannilinxx.todosimple.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public TaskService() {
    }

    public Task findById(Long id) {
        try {
            Optional<Task> task = this.taskRepository.findById(id);

            return task.orElseThrow(() -> new RuntimeException("Task not found"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to find task: " + e.getMessage());
        }
    }

    public List<Task> findByUser(Long id) {
        try {
            this.userService.findById(id);

            return this.taskRepository.findByUser_Id(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find tasks by user: " + e.getMessage());
        }
    }

    @Transactional
    public Task create(Task t) {
        try {
            User user = this.userService.findById(t.getUser().getId());

            t.setId(null);
            t.setUser(user);

            return this.taskRepository.save(t);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create task: " + e.getMessage());
        }
    }

    @Transactional
    public Task update(Task t) {
        try {
            User user = this.userService.findById(t.getUser().getId());

            Task newTask = findById(t.getId());
            newTask.setTitle(t.getTitle());
            newTask.setDescription(t.getDescription());
            newTask.setDate(t.getDate());
            newTask.setUser(user);

            return this.taskRepository.save(newTask);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update task: " + e.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            findById(id);

            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete task: " + e.getMessage());
        }
    }

}
