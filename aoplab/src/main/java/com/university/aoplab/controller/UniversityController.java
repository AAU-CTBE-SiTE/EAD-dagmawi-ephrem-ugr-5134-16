package com.university.aoplab.controller;

import com.university.aoplab.model.Student;
import com.university.aoplab.service.CourseService;
import com.university.aoplab.service.PaymentService;
import com.university.aoplab.service.StudentService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UniversityController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final PaymentService paymentService;

    public UniversityController(
            StudentService studentService,
            CourseService courseService,
            PaymentService paymentService) {

        this.studentService = studentService;
        this.courseService = courseService;
        this.paymentService = paymentService;
    }

    @PostMapping("/students")
    public Student registerStudent(
            @RequestParam String name,
            @RequestParam String email) {

        return studentService.registerStudent(name, email);
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id) {

        return studentService.getStudent(id);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    @PostMapping("/courses")
    public String createCourse(@RequestParam String title) {

        return courseService.createCourse(title);
    }

    @GetMapping("/courses/{id}")
    public String getCourse(@PathVariable Long id) {

        return courseService.getCourse(id);
    }

    @PostMapping("/payments")
    public String processPayment(
            @RequestParam Long studentId,
            @RequestParam double amount) {

        return paymentService.processPayment(studentId, amount);
    }
}