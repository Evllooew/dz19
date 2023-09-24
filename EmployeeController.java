package com.example.hw19;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        employeeService.addEmployee(firstName, lastName);
        return new Employee(firstName, lastName);
    }

    @RequestMapping("/remove")
    public ResponseEntity<Employee> removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @RequestMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName).getBody();
    }
    @GetMapping("/list")
    public List<Employee> listEmployees() {
        return employeeService.getAllEmployees();
    }
}
