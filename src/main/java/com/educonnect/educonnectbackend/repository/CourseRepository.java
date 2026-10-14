package com.educonnect.educonnectbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.educonnect.educonnectbackend.entity.Course;
public interface CourseRepository extends JpaRepository<Course, Long> {
}
