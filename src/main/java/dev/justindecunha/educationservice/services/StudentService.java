package dev.justindecunha.educationservice.services;

import dev.justindecunha.educationservice.domain.Student;
import dev.justindecunha.educationservice.repositories.StudentRepository;
import dev.justindecunha.educationservice.repositories.specifications.StudentFilter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * A service class to house business logic for student operations.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Finds a student by specified id, or throws EntityNotFound exception if not found.
     *
     * @param id The id of the student to find.
     * @return The student associated with the provided id.
     */
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " was not found"));
    }

    /**
     * Finds students matching the provided filter definition.
     *
     * @param filter The criteria to filter the students collection for.
     * @return A list of students matching the specified filter criteria.
     */
    public List<Student> findAll(StudentFilter filter) {
        return studentRepository.findAll(filter.toSpecification());
    }

    /**
     * Creates a student with the provided student information.
     *
     * @param student The student object to persist.
     * @return The saved student object.
     */
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Updates a student by the provided student id. Method is idempotent, and will not invoke the database if no change was made.
     *
     * @param id The id of the student to update.
     * @param updatedStudent The updated student object to persist.
     * @return The saved representation of the student, after updates.
     */
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
