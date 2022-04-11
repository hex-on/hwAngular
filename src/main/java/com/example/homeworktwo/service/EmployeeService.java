package com.example.homeworktwo.service;

import com.example.homeworktwo.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    Boolean deleteEmployee(Long id);

    Optional<Employee> getEmployee(Long id);

    List<Employee> getEmployee(String fname);

    void deleteAllEmployees();

    Boolean emailValidator(String email);

    Employee login(String email, String password);

    Optional<Employee> getEmployeeByToken(String token);
}
