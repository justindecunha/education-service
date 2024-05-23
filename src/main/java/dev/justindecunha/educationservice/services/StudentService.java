package dev.justindecunha.educationservice.services;

import dev.justindecunha.educationservice.domain.Student;
import dev.justindecunha.educationservice.repositories.StudentRepository;
import dev.justindecunha.educationservice.repositories.specifications.StudentFilter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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

    public List<Student> findAll(StudentFilter filter) {
        return studentRepository.findAll(filter.toSpecification());
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(new EntityNotFoundException("Student with id " + id + " was not found")));

        boolean isStudentUpdated = false;

        if (StringUtils.hasLength(updatedStudent.getName()) && !updatedStudent.getName().equals(existingStudent.getName())) {
            existingStudent.setName(updatedStudent.getName());
            isStudentUpdated = true;
        }

        if (updatedStudent.getDateOfBirth() != null && !updatedStudent.getDateOfBirth().equals(existingStudent.getDateOfBirth())) {
            existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
            isStudentUpdated = true;
        }

        if (updatedStudent.getJoiningDate() != null && !updatedStudent.getJoiningDate().equals(existingStudent.getJoiningDate())) {
            existingStudent.setJoiningDate(updatedStudent.getJoiningDate());
            isStudentUpdated = true;
        }

        if (StringUtils.hasLength(updatedStudent.getCourse()) && !updatedStudent.getCourse().equals(existingStudent.getCourse())) {
            existingStudent.setCourse(updatedStudent.getCourse());
            isStudentUpdated = true;
        }

        return isStudentUpdated ? studentRepository.save(existingStudent) : existingStudent;
    }

}
