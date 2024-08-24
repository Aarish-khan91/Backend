package com.main.entities;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(unique = true, nullable = false)
    private String courseCode;

    private String description;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private CourseInstances courseInstances;

    // Getters, Setters, Constructors through Lombok will be added automatically
}
