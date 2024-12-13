package com.JAVA_EVAL.JAVA.model;

import com.JAVA_EVAL.JAVA.view.SalaryView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(SalaryView.class)
    Integer id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Veuillez entrer votre email")
    @JsonView(SalaryView.class)
    String email;

    String password;

    String code;

    @ManyToOne
    Droit droit;

    @ManyToOne
    @JoinColumn(name = "corporation_id", nullable = true)
    private Corporation corporation;

    public void setCode(String code){
        this.code = code.toUpperCase();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Veuillez entrer votre email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Veuillez entrer votre email") String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public Droit getDroit() {
        return droit;
    }

    public void setDroit(Droit droit) {
        this.droit = droit;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }
}
