package org.project.coursesmanagementsystem.controllers;

import jakarta.validation.Valid;
import org.project.coursesmanagementsystem.model.admins.AdminsDTO;
import org.project.coursesmanagementsystem.model.courses.Courses;
import org.project.coursesmanagementsystem.model.courses.CoursesDTO;
import org.project.coursesmanagementsystem.model.students.Students;
import org.project.coursesmanagementsystem.model.students.StudentsDTO;
import org.project.coursesmanagementsystem.repository.CoursesRepository;
import org.project.coursesmanagementsystem.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    public AdminController(CoursesRepository coursesRepository, StudentsRepository studentsRepository) {
        this.coursesRepository = coursesRepository;
        this.studentsRepository = studentsRepository;
    }

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @PostMapping("/registerCourse")
    public ResponseEntity<?> registerCourse(@RequestBody CoursesDTO coursesDTO) {

        if(coursesDTO != null) {}
        Courses newCourse = new Courses(coursesDTO.courseName(), coursesDTO.courseDuration());
        this.coursesRepository.save(newCourse);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/listCourses")
    public ResponseEntity<Iterable> listCourses() {

        Iterable<Courses> coursesList = this.coursesRepository.findAll();

        return new ResponseEntity<>(coursesList, HttpStatus.OK);
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<Courses> updateCourse(@RequestBody CoursesDTO coursesDTO, @PathVariable(name = "id") Integer id) {

        Optional<Courses> courseToUpdate = this.coursesRepository.findById(id);
        if (courseToUpdate.isPresent()) {
            Courses courseUpdated = courseToUpdate.get();

            courseUpdated.setCourse_duration(coursesDTO.courseDuration());
            courseUpdated.setCourse_name(coursesDTO.courseName());

            this.coursesRepository.save(courseUpdated);

            return new ResponseEntity<>(courseUpdated, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteCourse")
    public ResponseEntity<?> deleteCourse(@RequestParam Integer id) {

        if (this.coursesRepository.existsById(id)) {
            this.coursesRepository.deleteById(id);
        }


        return ResponseEntity.noContent().build();
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerStudent(@RequestBody StudentsDTO studentsDTO) {

        if(studentsDTO != null) {
            Students newStudent = new Students(studentsDTO);
            this.studentsRepository.save(newStudent);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/listStudents")
    public ResponseEntity<Iterable> listStudents() {

        Iterable<Students> studentsList = this.studentsRepository.findAll();

        return ResponseEntity.ok(studentsList);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Students> updateStudent(@RequestBody StudentsDTO studentsDTO, @PathVariable(name = "id") Integer id) {

        if(this.studentsRepository.existsById(id)) {
            Optional<Students> studentToUpdate = this.studentsRepository.findById(id);
            Students studentUpdated = studentToUpdate.get();

            studentUpdated.setStudent_name(studentsDTO.studentName());
            studentUpdated.setStudent_age(studentsDTO.studentAge());

            this.studentsRepository.save(studentUpdated);

            return new ResponseEntity<>(studentUpdated, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<?> deleteStudent(@RequestParam Integer id) {

        if(this.studentsRepository.existsById(id)){
            this.studentsRepository.deleteById(id);
        };

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/listStudentsByCourse/{courseId}")
    public ResponseEntity<Iterable> listStudentsByCourse(@PathVariable(name = "courseId") Integer courseId) {

        Optional<Courses> courseById = this.coursesRepository.findById(courseId);
        if (courseById.isPresent()) {
            Courses course = courseById.get();

            // Convertendo para DTO para evitar referências cíclicas
            List<StudentsDTO> studentsList = course.getStudents().stream()
                    .map(StudentsDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(studentsList);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/enroll")
    public ResponseEntity<Students> enrollStudent(@RequestParam Integer studentId, @RequestParam Integer courseId) {

        if(this.studentsRepository.existsById(studentId)){

            Optional<Students> studentsOptional = this.studentsRepository.findById(studentId);
            Students students = studentsOptional.get();

            students.getCourses().add(this.coursesRepository.findById(courseId).get());

            this.studentsRepository.save(students);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/unenroll")
    public ResponseEntity<Students> unenrrollStudent(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        if(this.studentsRepository.existsById(studentId)){

            Optional<Students> studentsOptional = this.studentsRepository.findById(studentId);
            Students students = studentsOptional.get();

            if(this.coursesRepository.existsById(courseId)){

                Optional<Courses> coursesOptional = this.coursesRepository.findById(courseId);
                Courses courses = coursesOptional.get();

                students.getCourses().remove(courses);

                this.studentsRepository.save(students);

                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
