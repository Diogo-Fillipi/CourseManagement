package org.project.coursesmanagementsystem.repository;

import org.project.coursesmanagementsystem.model.students.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Integer> {
}
