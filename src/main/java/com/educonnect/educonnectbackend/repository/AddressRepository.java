package com.educonnect.educonnectbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.educonnect.educonnectbackend.entity.Address;
public interface AddressRepository extends JpaRepository<Address, Long> {
}
