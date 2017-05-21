package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

public List<Task> getAllTasks() {
    return taskRepository.findAll();
}

    public Task getTaskById(Long id) {
        return taskRepository.findOne(id);
    }

    public void removeTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    public void addTask(Task task) {

        taskRepository.save(task);
    }

    public void updateTask(Task update) {

        taskRepository.save(update);
    }
}

