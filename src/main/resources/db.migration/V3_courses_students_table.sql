CREATE TABLE Courses_Students{
    student_id INT,
    course_id INT,
    PRIMARY KEY(aluno_id, curso_id),
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES Students(id),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES Courses(id)
    }
