package com.example.homeworktwo.repo;

import com.example.homeworktwo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByFirstNameIgnoreCase(String fname);

    Employee findByEmailAndPassword(String email, String password);

    Optional<Employee> findEmployeeByToken(String token);

    List<Employee> findAllByOrderByIdAsc();
}
