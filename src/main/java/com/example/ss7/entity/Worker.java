package com.example.ss7.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String phone;
    private String address;
    private Double salary;
}
