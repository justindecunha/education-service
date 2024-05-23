package dev.justindecunha.educationservice.api.controllers;

import dev.justindecunha.educationservice.domain.Student;
import dev.justindecunha.educationservice.repositories.specifications.StudentFilter;
import dev.justindecunha.educationservice.services.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    List<Student> listStudents(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String course) {

        StudentFilter studentFilter = StudentFilter.builder()
                .name(name)
                .course(course)
                .build();

        return studentService.findAll(studentFilter);
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

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student updatedStudent) {
        try {

            Student savedStudent = studentService.updateStudent(id, updatedStudent);
            return ResponseEntity.ok(savedStudent);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
