package com.xxdannilinxx.todosimple.services;

import java.util.Optional;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxdannilinxx.todosimple.models.Task;
import com.xxdannilinxx.todosimple.models.User;
import com.xxdannilinxx.todosimple.repositories.TaskRepository;
import com.xxdannilinxx.todosimple.services.exceptions.ObjectNotFoundException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public TaskService() {
    }

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);

        return task.orElseThrow(() -> new ObjectNotFoundException("Task not found"));
    }

    public List<Task> findByUser(Long id) {
        this.userService.findById(id);

        return this.taskRepository.findByUser_Id(id);
    }

    @Transactional
    public Task create(Task t) {
        User user = this.userService.findById(t.getUser().getId());

        t.setId(null);
        t.setUser(user);

        return this.taskRepository.save(t);

    }

    @Transactional
    public Task update(Task t) {
        User user = this.userService.findById(t.getUser().getId());

        Task newTask = findById(t.getId());
        newTask.setTitle(t.getTitle());
        newTask.setDescription(t.getDescription());
        newTask.setDate(t.getDate());
        newTask.setUser(user);

        return this.taskRepository.save(newTask);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);

        this.taskRepository.deleteById(id);
    }
}
