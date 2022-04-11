package com.example.homeworktwo.service;

import com.example.homeworktwo.entity.Employee;
import com.example.homeworktwo.exception.EmployeeEmailValidationException;
import com.example.homeworktwo.exception.EmployeeNotfoundException;
import com.example.homeworktwo.repo.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Employee addEmployee(Employee employee) {

        if (!emailValidator(employee.getEmail())) {
            throw new EmployeeEmailValidationException();
        }
        employee.setToken(UUID.randomUUID().toString());
        employee.setIsAdmin(false);
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee newEmployee) {
        if (newEmployee.getEmail() != null && !emailValidator(newEmployee.getEmail())) {
            throw new EmployeeEmailValidationException();
        }
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotfoundException::new);
        if (newEmployee.getLastName() != null) employee.setLastName(newEmployee.getLastName());
        if (newEmployee.getFirstName() != null) employee.setFirstName(newEmployee.getFirstName());
        if (newEmployee.getEmail() != null) employee.setEmail(newEmployee.getEmail());
        if (newEmployee.getSalary() != null) employee.setSalary(newEmployee.getSalary());
        if (newEmployee.getPassword() != null) employee.setPassword(newEmployee.getPassword());
        if (newEmployee.getIsAdmin() != null) employee.setIsAdmin(newEmployee.getIsAdmin());
        return employeeRepository.save(employee);
    }

    @Override
    public Boolean deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

    @Override
    public List<Employee> getEmployee(String fname) {
        return employeeRepository.findAllByFirstNameIgnoreCase(fname);
    }

    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    @Override
    public Boolean emailValidator(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    @Override
    public Employee login(String email, String password) {
        Employee found = employeeRepository.findByEmailAndPassword(email, password);
        Employee send = new Employee(
                found.getId(),
                found.getFirstName(),
                found.getLastName(),
                found.getEmail(),
                found.getSalary(),
                "Hidden",
                found.getToken(),
                found.getIsAdmin()
        );
        return send;
    }

    @Override
    public Optional<Employee> getEmployeeByToken(String token) {
        return employeeRepository.findEmployeeByToken(token);
    }

}
