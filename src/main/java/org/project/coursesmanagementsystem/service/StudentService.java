package org.project.coursesmanagementsystem.service;

import org.project.coursesmanagementsystem.model.courses.Courses;
import org.project.coursesmanagementsystem.model.students.Students;
import org.project.coursesmanagementsystem.model.students.StudentsDTO;
import org.project.coursesmanagementsystem.repository.CoursesRepository;
import org.project.coursesmanagementsystem.repository.StudentsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentsRepository studentsRepository;
    private CoursesRepository coursesRepository;

    StudentService(StudentsRepository studentsRepository, CoursesRepository coursesRepository) {
        this.studentsRepository = studentsRepository;
        this.coursesRepository = coursesRepository;
    }

    public ResponseEntity<?> registerStudent(StudentsDTO studentsDTO) {
        if(studentsDTO != null) {
            Students newStudent = new Students(studentsDTO);
            this.studentsRepository.save(newStudent);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Iterable> listAllStudents() {

        Iterable<Students> studentsList = this.studentsRepository.findAll();

        return ResponseEntity.ok(studentsList);
    }

    public ResponseEntity<Students> updateStudent(StudentsDTO studentsDTO, Integer id) {
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

    public ResponseEntity<?> deleteStudent(Integer id) {
        if(this.studentsRepository.existsById(id)) {
            this.studentsRepository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Iterable> listStudentsByCourseId(Integer courseId) {
        Optional<Courses> courseById = this.coursesRepository.findById(courseId);
        if (courseById.isPresent()) {
            Courses course = courseById.get();

            List<StudentsDTO> studentsList = course.getStudents().stream()
                    .map(StudentsDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(studentsList);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Iterable> listEnrolledCourses (Integer studentId) {

    Optional<Students> studentsOptional = this.studentsRepository.findById(studentId);
    Students students = studentsOptional.get();

    Iterable<Courses> coursesList = students.getCourses();

        return new ResponseEntity<>(coursesList, HttpStatus.OK);
    }

}

