package com.example.homeworktwo.controller;

import com.example.homeworktwo.entity.Employee;
import com.example.homeworktwo.exception.EmployeeNotfoundException;
import com.example.homeworktwo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
@RestController
@CrossOrigin
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        if (employeeList.isEmpty()) {
            throw new EmployeeNotfoundException();
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> insertEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee e = employeeService.updateEmployee(id, employee);
        if (e == null) {
            throw new EmployeeNotfoundException();
        }
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        Boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        throw new EmployeeNotfoundException();
    }

    @GetMapping("/bulkinsert")
    public ResponseEntity<List<Employee>> bulkAddEmployee(@RequestBody List<Employee> employeeList) {
        employeeList.forEach(employee -> employeeService.addEmployee(employee));
        return new ResponseEntity<>(employeeList, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if (employee.isPresent())
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        throw new EmployeeNotfoundException();
    }

    @GetMapping("/gettoken/{token}")
    public ResponseEntity<?> getEmployeeByToken(@PathVariable("token") String token) {
        Optional<Employee> employee = employeeService.getEmployeeByToken(token);
        if (employee.isPresent())
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        throw new EmployeeNotfoundException();
    }

    @GetMapping("/getfname/{fname}")
    public ResponseEntity<List<Employee>> getEmployeeByFname(@PathVariable("fname") String fname) {
        List<Employee> employeeList = employeeService.getEmployee(fname);
        if (employeeList.isEmpty()) {
            throw new EmployeeNotfoundException();
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map map) {
        return new ResponseEntity<>(employeeService.login(map.get("email").toString(), map.get("password").toString()), HttpStatus.OK);
    }


}
