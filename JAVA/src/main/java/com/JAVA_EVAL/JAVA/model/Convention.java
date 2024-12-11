package com.JAVA_EVAL.JAVA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Convention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Veuillez entrer votre nom")
    String name;

    float Subvention;

    @Column(name = "max_salary")
    Integer MaxSalary;

    @ManyToOne
    @JoinColumn(name = "corporation_id")
    private Corporation corporation;

    @OneToMany(mappedBy = "convention")
    private List<Salary> salaries;

}
