package org.project.coursesmanagementsystem.model.students;

import jakarta.persistence.*;
import lombok.Data;
import org.project.coursesmanagementsystem.model.courses.Courses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Students")
@Data
public class Students {

    public Students() {}

    public Students(StudentsDTO studentsDTO) {
        this.student_name = studentsDTO.studentName();
        this.student_age = studentsDTO.studentAge();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_id;

    @Column(name = "student_name", nullable = false)
    private String student_name;

    @Column(name = "student_age", nullable = false)
    private Integer student_age;

    @ManyToMany
    @JoinTable(
            name = "Courses_Students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Courses> courses = new ArrayList<>();

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public Integer getStudent_age() {
        return student_age;
    }

    public void setStudent_age(Integer student_age) {
        this.student_age = student_age;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }
}
