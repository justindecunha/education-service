package dev.justindecunha.educationservice.api.controllers;

import dev.justindecunha.educationservice.domain.Student;
import dev.justindecunha.educationservice.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
