package org.project.coursesmanagementsystem.controllers;

import org.project.coursesmanagementsystem.model.courses.Courses;
import org.project.coursesmanagementsystem.model.courses.CoursesDTO;
import org.project.coursesmanagementsystem.model.students.Students;
import org.project.coursesmanagementsystem.model.students.StudentsDTO;
import org.project.coursesmanagementsystem.service.AdminService;
import org.project.coursesmanagementsystem.service.CourseService;
import org.project.coursesmanagementsystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    public AdminController() {

    }

    AdminService adminsService;
    CourseService coursesService;
    StudentService studentsService;

    @PostMapping("/registerCourse")
    public ResponseEntity<?> registerCourse(@RequestBody CoursesDTO coursesDTO) {
        return coursesService.registerCourse(coursesDTO);
    }

    @GetMapping("/listCourses")
    public ResponseEntity<Iterable> listCourses() {
        return coursesService.listCourses();
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<Courses> updateCourse(@RequestBody CoursesDTO coursesDTO, @PathVariable(name = "id") Integer id) {
        return this.coursesService.updateCourse(coursesDTO, id);
    }

    @DeleteMapping("/deleteCourse")
    public ResponseEntity<?> deleteCourse(@RequestParam Integer id) {
        return this.coursesService.deleteCourse(id);
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerStudent(@RequestBody StudentsDTO studentsDTO) {
        return studentsService.registerStudent(studentsDTO);
    }

    @GetMapping("/listStudents")
    public ResponseEntity<Iterable> listStudents() {
        return this.studentsService.listAllStudents();
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Students> updateStudent(@RequestBody StudentsDTO studentsDTO, @PathVariable(name = "id") Integer id) {
        return this.studentsService.updateStudent(studentsDTO, id);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<?> deleteStudent(@RequestParam Integer id) {
        return this.studentsService.deleteStudent(id);
    }


    @GetMapping("/listStudentsByCourse/{courseId}")
    public ResponseEntity<Iterable> listStudentsByCourse(@PathVariable(name = "courseId") Integer courseId) {
        return this.studentsService.listStudentsByCourseId(courseId);
    }

    @PostMapping("/enroll")
    public ResponseEntity<?> enrollStudent(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        return this.adminsService.enrollStudent(studentId, courseId);
    }

    @DeleteMapping("/unenroll")
    public ResponseEntity<?> unenrrollStudent(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        return this.adminsService.unenrrollStudent(studentId, courseId);
    }
}