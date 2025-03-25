package org.project.coursesmanagementsystem.model.courses;

import jakarta.persistence.*;
import org.project.coursesmanagementsystem.model.students.Students;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Courses")
public class Courses {

    public Courses() {}

    public Courses(String name, String duration) {
        this.course_name = name;
        this.course_duration = duration;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer course_id;

    @Column(name = "course_name", nullable = false)
    private String course_name;

    @Column(name = "course_duration", nullable = false)
    private String course_duration;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Students> students = new ArrayList<>();


    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_duration() {
        return course_duration;
    }

    public void setCourse_duration(String course_duration) {
        this.course_duration = course_duration;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

}
