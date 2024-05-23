package dev.justindecunha.educationservice.services;

import dev.justindecunha.educationservice.domain.Student;
import dev.justindecunha.educationservice.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " was not found"));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

}