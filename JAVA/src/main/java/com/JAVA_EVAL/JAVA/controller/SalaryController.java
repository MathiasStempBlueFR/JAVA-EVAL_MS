package com.JAVA_EVAL.JAVA.controller;

import com.JAVA_EVAL.JAVA.dao.ConventionDao;
import com.JAVA_EVAL.JAVA.dao.SalaryDao;
import com.JAVA_EVAL.JAVA.model.Convention;
import com.JAVA_EVAL.JAVA.model.Salary;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class SalaryController {

    @Autowired
    private SalaryDao salaryDao;

    @Autowired
    private ConventionDao conventionDao;

    @GetMapping("/salary")
    public List<Salary> getAll(){
        return salaryDao.findAll();
    }

    @GetMapping("salary/{id}")
    public ResponseEntity<Salary> get(@PathVariable Integer id){
        Optional<Salary> optionalSalary = salaryDao.findById(id);
        if (optionalSalary.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalSalary.get(), HttpStatus.OK);
    }

    @PostMapping("/salary")
    public ResponseEntity<Salary> create(
            @RequestBody @Valid Salary salary){
        // Vérification que la convention existe
        Optional<Convention> optionalConvention = conventionDao.findById(salary.getConvention().getId());
        if (optionalConvention.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        salary.setId(null);
        salary.setMatricule(salary.getMatricule());
        salary.setBarCode(salary.getBarCode());
        salary.setUser(salary.getUser());
        salary.setConvention(salary.getConvention());
        salaryDao.save(salary);
        return new ResponseEntity<>(salary, HttpStatus.CREATED);
    }

    @PutMapping("/salary/{id}")
    public ResponseEntity<Salary> update(
            @RequestBody @Valid Salary salarySend,
            @PathVariable Integer id) {
        salarySend.setId(id);
        Optional<Salary> optionalSalary = salaryDao.findById(id);

        if ((optionalSalary.isEmpty())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        salaryDao.save(salarySend);
        return new ResponseEntity<>(salarySend, HttpStatus.OK);
    }

    @DeleteMapping("salary/{id}")
    public ResponseEntity<Salary> delete(@PathVariable Integer id){
        Optional<Salary> optionalSalary = salaryDao.findById(id);
        if (optionalSalary.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        salaryDao.deleteById(id);
        return new ResponseEntity<>(optionalSalary.get(), HttpStatus.OK);
    }
}
