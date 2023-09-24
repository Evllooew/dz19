package com.example.hw19;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    private static final int MAX_EMPLOYEES = 10;
    public ResponseEntity<String> addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEES) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Хранилище для сотрудников заполнено.");
        }

        if (employees.stream().anyMatch(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Сотрудник уже существует.");
        }

        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Сотрудник успешно добавлен.");
    }

    public ResponseEntity<Employee> removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);

        if (employeeToRemove == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        employees.remove(employeeToRemove);
        return ResponseEntity.status(HttpStatus.OK).body(employeeToRemove);
    }

    public ResponseEntity<Employee> findEmployee(String firstName, String lastName) {
        Employee employee = employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}

