package com.educonnect.educonnectbackend.config;

import com.educonnect.educonnectbackend.entity.*;
import com.educonnect.educonnectbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void run(String... args) {
        // Seed default users if none exist
        if (userRepository.count() == 0) {
            User admin = new User("admin", "admin123", "Admin User", "ADMIN");
            User student = new User("student", "student123", "John Doe", "STUDENT");
            userRepository.saveAll(List.of(admin, student));
        }

        // Seed sample departments if none exist
        Department cse;
        if (departmentRepository.count() == 0) {
            cse = departmentRepository.save(new Department(null, "Computer Science and Engineering"));
            departmentRepository.save(new Department(null, "Information Technology"));
            departmentRepository.save(new Department(null, "Electronics and Communication"));
        } else {
            cse = departmentRepository.findAll().get(0);
        }

        // Seed sample courses if none exist
        Course c1, c2;
        if (courseRepository.count() == 0) {
            c1 = courseRepository.save(new Course(null, "Data Structures and Algorithms", 4, null));
            c2 = courseRepository.save(new Course(null, "Full Stack Web Development", 4, null));
            courseRepository.save(new Course(null, "Database Management Systems", 3, null));
        } else {
            List<Course> courses = courseRepository.findAll();
            c1 = courses.get(0);
            c2 = courses.size() > 1 ? courses.get(1) : c1;
        }

        // Seed sample student if none exist
        if (studentRepository.count() == 0) {
            Address addr = addressRepository.save(new Address(null, "Hyderabad", "Telangana", "500001"));
            Student sampleStudent = new Student();
            sampleStudent.setName("John Doe");
            sampleStudent.setEmail("student@uniconnect.edu");
            sampleStudent.setDepartment(cse);
            sampleStudent.setAddress(addr);
            sampleStudent.setCourses(List.of(c1, c2));
            studentRepository.save(sampleStudent);

            Address addr2 = addressRepository.save(new Address(null, "Bangalore", "Karnataka", "560001"));
            Student sampleStudent2 = new Student();
            sampleStudent2.setName("Jane Smith");
            sampleStudent2.setEmail("jane@uniconnect.edu");
            sampleStudent2.setDepartment(cse);
            sampleStudent2.setAddress(addr2);
            sampleStudent2.setCourses(List.of(c1));
            studentRepository.save(sampleStudent2);
        }
    }
}
