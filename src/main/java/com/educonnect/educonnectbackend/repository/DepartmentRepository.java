package com.educonnect.educonnectbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.educonnect.educonnectbackend.entity.Department;
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
