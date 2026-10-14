package com.educonnect.educonnectbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.educonnect.educonnectbackend.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Pageable pageable);
}
