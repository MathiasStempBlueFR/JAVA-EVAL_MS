package com.JAVA_EVAL.JAVA.model;

import com.JAVA_EVAL.JAVA.view.CorporationView;
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
    @JsonView({SalaryView.class, CorporationView.class})
    Integer id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Veuillez entrer votre nom")
    @JsonView({SalaryView.class, CorporationView.class})
    String name;

    @OneToMany(mappedBy = "corporation")
    private List<Convention> conventions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Veuillez entrer votre nom") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Veuillez entrer votre nom") String name) {
        this.name = name;
    }

    public List<Convention> getConventions() {
        return conventions;
    }

    public void setConventions(List<Convention> conventions) {
        this.conventions = conventions;
    }
}
