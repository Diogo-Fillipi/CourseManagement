package org.project.coursesmanagementsystem.service;

import org.project.coursesmanagementsystem.model.courses.Courses;
import org.project.coursesmanagementsystem.model.courses.CoursesDTO;
import org.project.coursesmanagementsystem.repository.CoursesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    private CoursesRepository coursesRepository;

    CourseService(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public ResponseEntity<?> registerCourse(CoursesDTO courseDTO) {
        if(courseDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Courses newCourse = new Courses(courseDTO.courseName(), courseDTO.courseDuration());
        this.coursesRepository.save(newCourse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Iterable> listCourses() {
        Iterable<Courses> coursesList = this.coursesRepository.findAll();

        return new ResponseEntity<>(coursesList, HttpStatus.OK);
    }

    public ResponseEntity<Courses> updateCourse (CoursesDTO coursesDTO, Integer id) {
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

    public ResponseEntity<?> deleteCourse(Integer id) {
        if (this.coursesRepository.existsById(id)) {
            this.coursesRepository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }
}
