package com.employeemanagement.employeemanagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Feedback {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    private int rating;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "given_id")
    private User givenBy;

    @ManyToOne
    @JoinColumn(name = "given_to")
    private User givenTo;
}
