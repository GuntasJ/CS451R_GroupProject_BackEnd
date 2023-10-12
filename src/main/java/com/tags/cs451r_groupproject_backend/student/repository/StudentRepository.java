package com.tags.cs451r_groupproject_backend.student.repository;

import com.tags.cs451r_groupproject_backend.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
