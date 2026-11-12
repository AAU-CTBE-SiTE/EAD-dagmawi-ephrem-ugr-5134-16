package com.university.aoplab.service;

import com.university.aoplab.annotation.Auditable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CourseService {

    private final Map<Long, String> courses = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    @Auditable("COURSE_CREATION")
    public String createCourse(String title) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Course title cannot be empty");
        }

        Long id = idGenerator.getAndIncrement();

        String course = id + ": " + title;

        courses.put(id, course);

        return course;
    }

    public String getCourse(Long id) {

        String course = courses.get(id);

        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }

        return course;
    }
}