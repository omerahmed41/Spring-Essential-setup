package com.osuliman.spring.rest.essential.infrastructure;

import com.osuliman.spring.rest.essential.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {
}
