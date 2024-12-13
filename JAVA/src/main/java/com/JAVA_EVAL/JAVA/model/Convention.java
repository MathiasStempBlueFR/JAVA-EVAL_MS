package com.JAVA_EVAL.JAVA.model;

import com.JAVA_EVAL.JAVA.view.ConventionView;
import com.JAVA_EVAL.JAVA.view.SalaryView;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Convention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({SalaryView.class, ConventionView.class})
    Integer id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Veuillez entrer votre nom")
    @JsonView({SalaryView.class, ConventionView.class})
    String name;

    @JsonView({SalaryView.class, ConventionView.class})
    float Subvention;

    @JsonView({SalaryView.class, ConventionView.class})
    @Column(name = "max_salary")
    Integer MaxSalary;

    @ManyToOne
    @JoinColumn(name = "corporation_id")
    @JsonView(SalaryView.class)
    private Corporation corporation;

    @OneToMany(mappedBy = "convention")
    private List<Salary> salaries;

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

    public float getSubvention() {
        return Subvention;
    }

    public void setSubvention(float subvention) {
        Subvention = subvention;
    }

    public Integer getMaxSalary() {
        return MaxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        MaxSalary = maxSalary;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }
}
