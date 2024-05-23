package dev.justindecunha.educationservice.api.controllers;

import dev.justindecunha.educationservice.domain.Student;
import dev.justindecunha.educationservice.services.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Student> getStudent(@PathVariable Long id) {
        try {

            return ResponseEntity.ok(studentService.findById(id));

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {

            Student savedStudent = studentService.createStudent(student);

            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
