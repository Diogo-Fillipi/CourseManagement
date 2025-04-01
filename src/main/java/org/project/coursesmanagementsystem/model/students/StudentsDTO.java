package org.project.coursesmanagementsystem.model.students;

public record StudentsDTO(
        String studentName,
        int studentAge
) {
    public StudentsDTO(Students students) {
        this(students.getStudent_name(), students.getStudent_age());
    }
}
