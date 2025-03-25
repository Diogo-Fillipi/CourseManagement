package org.project.coursesmanagementsystem.controllers;

import org.project.coursesmanagementsystem.model.courses.Courses;
import org.project.coursesmanagementsystem.model.students.Students;
import org.project.coursesmanagementsystem.model.students.StudentsDTO;
import org.project.coursesmanagementsystem.repository.CoursesRepository;
import org.project.coursesmanagementsystem.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    public StudentController(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    @GetMapping("/listEnrolledCourse")
    public ResponseEntity<Iterable> getEnrolledCourse(@RequestParam int studentId) {

        Optional<Students> studentsOptional = this.studentsRepository.findById(studentId);
        Students students = studentsOptional.get();

        Iterable<Courses> coursesList = students.getCourses();

        return new ResponseEntity<>(coursesList, HttpStatus.OK);
    }


}
