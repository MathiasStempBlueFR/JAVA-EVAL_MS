package com.JAVA_EVAL.JAVA.controller;

import com.JAVA_EVAL.JAVA.dao.CorporationDao;
import com.JAVA_EVAL.JAVA.model.Convention;
import com.JAVA_EVAL.JAVA.model.Corporation;
import com.JAVA_EVAL.JAVA.model.Salary;
import com.JAVA_EVAL.JAVA.security.IsAdmin;
import com.JAVA_EVAL.JAVA.security.IsCorpo;
import com.JAVA_EVAL.JAVA.view.CorporationView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CorporationController {

    @Autowired
    private CorporationDao corporationDao;

    @IsCorpo
    @GetMapping("/corpo")
    @JsonView(CorporationView.class)
    public List<Corporation> getAll(){
        return corporationDao.findAll();
    }

    @IsCorpo
    @GetMapping("/corpo/{id}")
    public ResponseEntity<Corporation> get(@PathVariable Integer id){
        Optional<Corporation> optionalCorporation = corporationDao.findById(id);
        if (optionalCorporation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalCorporation.get(), HttpStatus.OK);
    }

    @IsAdmin
    @PostMapping("/corpo")
    public ResponseEntity<Corporation> create(
            @RequestBody @Valid Corporation corporation){
        corporation.setId(null);
        corporation.setName(corporation.getName());
        if (corporationDao.findByName(corporation.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        corporationDao.save(corporation);
        return new ResponseEntity<>(corporation, HttpStatus.CREATED);
    }

    @IsAdmin
    @PutMapping("/corpo/{id}")
    public ResponseEntity<Corporation> update(
            @RequestBody @Valid Corporation corpoSend, @PathVariable Integer id){
        corpoSend.setId(id);
        Optional<Corporation> optionalCorporation = corporationDao.findById(id);
        if (optionalCorporation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (corporationDao.findByName(corpoSend.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        corporationDao.save(corpoSend);
        return new ResponseEntity<>(optionalCorporation.get(), HttpStatus.OK);
    }

    @IsAdmin
    @DeleteMapping("/corpo/{id}")
    public ResponseEntity<Corporation> delete(@PathVariable Integer id){
        Optional<Corporation> optionalCorporation = corporationDao.findById(id);
        if (optionalCorporation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        corporationDao.deleteById(id);
        return new ResponseEntity<>(optionalCorporation.get(), HttpStatus.OK);
    }

}
