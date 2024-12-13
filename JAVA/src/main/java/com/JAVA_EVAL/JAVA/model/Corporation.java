package com.JAVA_EVAL.JAVA.model;

import com.JAVA_EVAL.JAVA.view.SalaryView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.Internal;

import java.util.List;

@Entity
public class Corporation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(SalaryView.class)
    Integer id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Veuillez entrer votre nom")
    @JsonView(SalaryView.class)
    String name;

    @OneToMany(mappedBy = "corporation")
    private List<Convention> conventions;
}
