package com.educonnect.educonnectbackend.mapper;
import com.educonnect.educonnectbackend.dto.*;
import com.educonnect.educonnectbackend.entity.Address;
import com.educonnect.educonnectbackend.entity.Department;
import com.educonnect.educonnectbackend.entity.Student;

import java.util.List;
public class StudentMapper {
    public static StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());

        if(student.getDepartment() != null) {
            Department d = student.getDepartment();
            dto.setDepartment(new DepartmentDTO(d.getId(), d.getName()));
        }
        if(student.getAddress() != null) {
            Address a = student.getAddress();
            dto.setAddress(new AddressDTO(a.getId(), a.getCity(), a.getState(), a.getPincode()));
        }
        if(student.getCourses() != null) {
            List<CourseDTO> courseDTOs = student.getCourses().stream()
                .map(c -> new CourseDTO(c.getId(), c.getTitle(), c.getCredits()))
                .toList();
            dto.setCourses(courseDTOs);
        }
        return dto;
    }
}
