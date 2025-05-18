package org.project.coursesmanagementsystem.controllers;

import org.project.coursesmanagementsystem.model.courses.Courses;
import org.project.coursesmanagementsystem.model.students.Students;
import org.project.coursesmanagementsystem.model.students.StudentsDTO;
import org.project.coursesmanagementsystem.repository.CoursesRepository;
import org.project.coursesmanagementsystem.repository.StudentsRepository;
import org.project.coursesmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
    this.studentService = studentService;
    }

    @GetMapping("/listEnrolledCourse")
    public ResponseEntity<Iterable> getEnrolledCourse(@RequestParam int studentId) {
        return this.studentService.listEnrolledCourses(studentId);
    }

}



