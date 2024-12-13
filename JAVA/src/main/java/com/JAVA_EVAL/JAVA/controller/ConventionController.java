package com.JAVA_EVAL.JAVA.controller;

import com.JAVA_EVAL.JAVA.dao.ConventionDao;
import com.JAVA_EVAL.JAVA.model.Convention;
import com.JAVA_EVAL.JAVA.model.Corporation;
import com.JAVA_EVAL.JAVA.security.IsAdmin;
import com.JAVA_EVAL.JAVA.security.IsCorpo;
import com.JAVA_EVAL.JAVA.view.ConventionView;
import com.JAVA_EVAL.JAVA.view.CorporationView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ConventionController {

    @Autowired
    private ConventionDao conventionDao;

    @IsCorpo
    @GetMapping("/convention")
    @JsonView(ConventionView.class)
    public List<Convention> getAll() {return conventionDao.findAll();}

    @IsCorpo
    @GetMapping("/convention/{id}")
    public ResponseEntity<Convention> get(@PathVariable Integer id){
        Optional<Convention> optionalConvention = conventionDao.findById(id);
        if (optionalConvention.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalConvention.get(), HttpStatus.OK);
    }

    @IsAdmin
    @PostMapping("/convention")
    public ResponseEntity<Convention> create(
            @RequestBody @Valid Convention convention){
        convention.setId(null);
        convention.setName(convention.getName());
        convention.setSubvention(convention.getSubvention());
        if (convention.getSubvention() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Subventions négatives
        }
        convention.setMaxSalary(convention.getMaxSalary());
        if (convention.getMaxSalary() < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Nombre max de salariés invalide
        }
        conventionDao.save(convention);
        return new ResponseEntity<>(convention, HttpStatus.CREATED);
    }

    @IsAdmin
    @PutMapping("/convention/{id}")
    public ResponseEntity<Convention> update(
            @RequestBody @Valid Convention conventionSend, @PathVariable Integer id){
        conventionSend.setId(id);
        Optional<Convention> optionalConvention = conventionDao.findById(id);
        if(optionalConvention.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (conventionSend.getSubvention() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Subventions négatives
        }
        if (conventionSend.getMaxSalary() < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Nombre max de salariés invalide
        }
        conventionDao.save(conventionSend);
        return new ResponseEntity<>(conventionSend, HttpStatus.OK);
    }

    @IsAdmin
    @DeleteMapping("/convention/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        Optional<Convention> optionalConvention = conventionDao.findById(id);
        if (optionalConvention.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        conventionDao.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
