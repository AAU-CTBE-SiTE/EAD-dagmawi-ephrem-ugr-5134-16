package com.university.aoplab.service;

import com.university.aoplab.annotation.Auditable;
import com.university.aoplab.model.Student;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private final Map<Long, Student> students = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    @Auditable("STUDENT_REGISTRATION")
    public Student registerStudent(String name, String email) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        Long id = idGenerator.getAndIncrement();

        Student student = new Student(id, name, email);

        students.put(id, student);

        return student;
    }

    public Student getStudent(Long id) {

        Student student = students.get(id);

        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        return student;
    }

    public void deleteStudent(Long id) {

        if (students.remove(id) == null) {
            throw new IllegalArgumentException("Student not found");
        }
    }

    public Student testSelfInvocation() {

        return this.registerStudent(
                "Internal Student",
                "internal@example.com"
        );
    }
}