package com.JAVA_EVAL.JAVA.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

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
}
