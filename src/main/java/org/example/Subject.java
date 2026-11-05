package org.example;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Subject {
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }
}