package dev.justindecunha.educationservice.repositories;

import dev.justindecunha.educationservice.domain.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * A basic CRUD repository for the students collection.
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Long>, JpaSpecificationExecutor<Student> {
}
