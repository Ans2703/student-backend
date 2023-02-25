package com.student.backend.service;

import com.student.backend.model.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Student student, Long id);

    void deleteStudentById(Long id);

}
