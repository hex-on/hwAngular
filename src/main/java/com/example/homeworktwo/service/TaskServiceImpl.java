package com.example.homeworktwo.service;

import com.example.homeworktwo.entity.Employee;
import com.example.homeworktwo.entity.Task;
import com.example.homeworktwo.repo.EmployeeRepository;
import com.example.homeworktwo.repo.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public TaskServiceImpl(TaskRepository taskRepository,EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Task> getAllTasks(String token) {
        Employee employee = employeeRepository.findEmployeeByToken(token).get();
        return taskRepository.findAllByEmployeeId(employee.getId());
    }

    @Override
    public void addTask(String token, Task task) {
        Employee employee = employeeRepository.findEmployeeByToken(token).get();
        task.setEmployeeId(employee.getId());
        taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task newTask = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("nu exista"));
        if (task.getEmployeeId() != null) newTask.setEmployeeId(task.getEmployeeId());
        if (task.getStatus() > 0) newTask.setStatus(task.getStatus());
        if (task.getProgress() > 0) newTask.setProgress(task.getProgress());
        if (task.getTitle() != null) newTask.setTitle(task.getTitle());
        if (task.getDescription() != null) newTask.setDescription(task.getDescription());

        return taskRepository.save(newTask);
    }
}
