package com.student.backend.studentService.impl;

import com.student.backend.exception.ResourceNotFoundException;
import com.student.backend.model.Student;
import com.student.backend.repository.StudentRepository;
import com.student.backend.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();

        } else {
            throw new ResourceNotFoundException("Student", "Student", student);
        }
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        Student exsistingStudent = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", "Student", student));
        exsistingStudent.setFirstName(student.getFirstName());
        exsistingStudent.setLastName(student.getLastName());
        exsistingStudent.setEmail(student.getEmail());
        studentRepository.save(exsistingStudent);
        return exsistingStudent;


    }

    @Override
    public void deleteStudentById(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("id", "id", id);
        }
    }
}
