package com.example.homeworktwo.controller;

import com.example.homeworktwo.entity.Task;
import com.example.homeworktwo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all/{token}")
    public ResponseEntity<?> getAllTasks(@PathVariable("token") String token) {
        List<Task> taskList = taskService.getAllTasks(token);
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @PostMapping("/add/{token}")
    public ResponseEntity<?> addTask(@PathVariable("token") String token, @RequestBody Task task) {
        taskService.addTask(token, task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> addTask(@PathVariable("id") Long id, @RequestBody Task task) {
        return new ResponseEntity<>(taskService.updateTask(id, task), HttpStatus.OK);
    }
}
