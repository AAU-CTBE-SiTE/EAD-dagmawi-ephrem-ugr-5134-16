
package com.example.studentapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
}
