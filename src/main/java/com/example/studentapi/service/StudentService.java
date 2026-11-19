
package com.example.studentapi.service;

import com.example.studentapi.dto.StudentRequestDto;
import com.example.studentapi.dto.StudentResponseDto;
import com.example.studentapi.mapper.StudentMapper;
import com.example.studentapi.model.Student;
import com.example.studentapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponseDto createStudent(StudentRequestDto dto) {
        Student student = StudentMapper.toEntity(dto);

        Student savedStudent = studentRepository.save(student);

        return StudentMapper.toResponseDTO(savedStudent);
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toResponseDTO)
                .toList();
    }

    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        return StudentMapper.toResponseDTO(student);
    }

    public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.toResponseDTO(updatedStudent);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }

        studentRepository.deleteById(id);
    }
}