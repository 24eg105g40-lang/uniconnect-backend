package com.educonnect.educonnectbackend.controller;

import com.educonnect.educonnectbackend.entity.Student;
import com.educonnect.educonnectbackend.entity.User;
import com.educonnect.educonnectbackend.repository.StudentRepository;
import com.educonnect.educonnectbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/students")
    public List<Map<String, Object>> getAdminStudents() {
        List<Student> students = studentRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        if (students.isEmpty()) {
            // Also check users with role STUDENT
            List<User> studentUsers = userRepository.findAll().stream()
                    .filter(u -> "STUDENT".equalsIgnoreCase(u.getRole()))
                    .toList();

            for (User u : studentUsers) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", u.getId());
                map.put("username", u.getUsername());
                map.put("name", u.getName());
                map.put("email", u.getUsername() + "@uniconnect.edu");
                result.add(map);
            }
        } else {
            for (Student s : students) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", s.getId());
                map.put("username", s.getEmail() != null ? s.getEmail() : "student_" + s.getId());
                map.put("name", s.getName());
                map.put("email", s.getEmail());
                if (s.getDepartment() != null) {
                    map.put("department", s.getDepartment().getName());
                }
                result.add(map);
            }
        }

        return result;
    }
}
