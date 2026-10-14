package com.educonnect.educonnectbackend.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    private DepartmentDTO department;
    private AddressDTO address;
    private List<CourseDTO> courses;
}
