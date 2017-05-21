package com.example.todo.controller;

import com.example.todo.utils.ResponseResult;
import com.example.todo.exception.TaskRepositoryException;
import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import com.example.todo.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public ResponseResult getTask(@PathVariable("taskId") Long id) {
        try {
            return ResponseBuilder.success(taskService.getTaskById(id));
        } catch (TaskRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseResult deleteTask(@PathVariable("taskId") Long id) {
        try {
            taskService.removeTask(id);
            return ResponseBuilder.success("Task successfully removed");

        } catch (TaskRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PostMapping
    public ResponseResult createTask(@RequestBody Task task) {
        try {
            taskService.addTask(task);
            return ResponseBuilder.success("Task %s successfully added ", task.getTitle());
        } catch (TaskRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PutMapping
    public ResponseResult updateUser(@RequestBody Task update) {
        try {
            taskService.updateTask(update);

            return ResponseBuilder.success("Task %s successfully updated", update.getTitle());
        } catch (TaskRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}
