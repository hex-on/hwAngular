package com.example.homeworktwo.service;

import com.example.homeworktwo.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface TaskService {
    List<Task> getAllTasks(String token);

    void addTask(String token, Task task);

    Task updateTask(Long id, Task task);
}
