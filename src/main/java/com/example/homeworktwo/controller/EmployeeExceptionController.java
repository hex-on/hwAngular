package com.example.homeworktwo.controller;

import com.example.homeworktwo.exception.EmployeeEmailValidationException;
import com.example.homeworktwo.exception.EmployeeNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class EmployeeExceptionController {
    @ExceptionHandler(value = EmployeeNotfoundException.class)
    public ResponseEntity<Object> exception(EmployeeNotfoundException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "employee not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmployeeEmailValidationException.class)
    public ResponseEntity<Object> exception(EmployeeEmailValidationException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "email not valid");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
