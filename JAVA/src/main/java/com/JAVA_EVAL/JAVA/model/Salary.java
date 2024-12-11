package com.JAVA_EVAL.JAVA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Veuillez entrer votre matricule")
    String matricule;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Veuillez mettre le code barre")
    String BarCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "convention_id", nullable = false)
    private Convention convention;
}
