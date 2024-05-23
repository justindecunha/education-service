package dev.justindecunha.educationservice.repositories.specifications;

import dev.justindecunha.educationservice.domain.Student;
import jakarta.persistence.criteria.Predicate;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A filter class to hold filtering criteria for the students collection.
 */
@Builder
public class StudentFilter {

    private String name;
    private String course;

    public Specification<Student> toSpecification() {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(name)) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }

            if (StringUtils.hasLength(course)) {
                predicates.add(criteriaBuilder.equal(root.get("course"), course));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

}
