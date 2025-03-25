CREATE TABLE Students{
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100),
    student_age INT,
    course_id INT,
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES Courses(id)
    }