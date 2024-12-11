package com.JAVA_EVAL.JAVA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.Internal;

import java.util.List;

@Entity
public class Corporation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Veuillez entrer votre nom")
    String name;

    @OneToMany(mappedBy = "corporation")
    private List<Convention> conventions;
}
