package org.project.coursesmanagementsystem.service;

import org.project.coursesmanagementsystem.model.courses.Courses;
import org.project.coursesmanagementsystem.model.students.Students;
import org.project.coursesmanagementsystem.repository.AdminsRepository;
import org.project.coursesmanagementsystem.repository.CoursesRepository;
import org.project.coursesmanagementsystem.repository.StudentsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class AdminService {

    private AdminsRepository adminsRepository;
    private StudentsRepository studentsRepository;
    private CoursesRepository coursesRepository;

    AdminService(AdminsRepository adminsRepository, StudentsRepository studentsRepository, CoursesRepository coursesRepository) {
        this.adminsRepository = adminsRepository;
        this.studentsRepository = studentsRepository;
        this.coursesRepository = coursesRepository;

    }

    public ResponseEntity<?> enrollStudent(Integer studentId, Integer courseId) {

        if(this.studentsRepository.existsById(studentId)){

            Optional<Students> studentsOptional = this.studentsRepository.findById(studentId);
            Students students = studentsOptional.get();

            students.getCourses().add(this.coursesRepository.findById(courseId).get());

            this.studentsRepository.save(students);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> unenrrollStudent(Integer studentId, Integer courseId) {
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
