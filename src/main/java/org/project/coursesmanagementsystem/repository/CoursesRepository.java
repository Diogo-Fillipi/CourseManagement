package org.project.coursesmanagementsystem.repository;

import org.project.coursesmanagementsystem.model.courses.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoursesRepository extends JpaRepository<Courses, Integer>{
}
