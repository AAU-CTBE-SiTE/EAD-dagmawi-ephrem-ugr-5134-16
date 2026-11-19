package com.example.studentapi.mapper;

import com.example.studentapi.dto.StudentRequestDto;
import com.example.studentapi.dto.StudentResponseDto;
import com.example.studentapi.model.Student;

public class StudentMapper {

    public static Student toEntity(StudentRequestDto dto) {
        Student student = new Student();

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());

        return student;
    }

    public static StudentResponseDto toResponseDTO(Student student) {
        StudentResponseDto dto = new StudentResponseDto();

        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());

        return dto;
    }
}