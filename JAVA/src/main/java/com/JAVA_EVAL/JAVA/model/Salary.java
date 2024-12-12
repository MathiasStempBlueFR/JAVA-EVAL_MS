package com.JAVA_EVAL.JAVA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "convention_id", nullable = true)
    private Convention convention;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Veuillez entrer votre matricule") String getMatricule() {
        return matricule;
    }

    public void setMatricule(@NotBlank(message = "Veuillez entrer votre matricule") String matricule) {
        this.matricule = matricule;
    }

    public @NotBlank(message = "Veuillez mettre le code barre") String getBarCode() {
        return BarCode;
    }

    public void setBarCode(@NotBlank(message = "Veuillez mettre le code barre") String barCode) {
        BarCode = barCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }
}
