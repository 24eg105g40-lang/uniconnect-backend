package com.educonnect.educonnectbackend.dto;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String title;
    private int credits;
}
