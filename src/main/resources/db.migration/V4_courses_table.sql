CREATE TABLE Courses{
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100),
    course_duration VARCHAR(100),
    student_id INT,
    CONSTRAINT fk_student FOREIGN KEY student_id REFERENCES Students(id)
    }