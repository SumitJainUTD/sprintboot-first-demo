package controller;

import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.EmployeeService;

import java.net.URI;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> read(@PathVariable(name = "id") Long id) {
        Employee foundEmployee = service.read(id);
        if (foundEmployee == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundEmployee);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee student) {
        Employee createdEmployee = service.create(student);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEmployee.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdEmployee);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee student, @PathVariable(name = "id") Long id) {
        Employee updatedEmployee = service.update(id, student);
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedEmployee);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(name = "id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
