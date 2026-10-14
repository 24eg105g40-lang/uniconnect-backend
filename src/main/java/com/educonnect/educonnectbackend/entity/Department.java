package com.educonnect.educonnectbackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
