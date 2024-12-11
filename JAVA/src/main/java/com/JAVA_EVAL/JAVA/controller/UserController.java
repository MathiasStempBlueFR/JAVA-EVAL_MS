package com.JAVA_EVAL.JAVA.controller;

import com.JAVA_EVAL.JAVA.dao.ConventionDao;
import com.JAVA_EVAL.JAVA.dao.CorporationDao;
import com.JAVA_EVAL.JAVA.dao.SalaryDao;
import com.JAVA_EVAL.JAVA.dao.UserDao;
import com.JAVA_EVAL.JAVA.model.Corporation;
import com.JAVA_EVAL.JAVA.model.Salary;
import com.JAVA_EVAL.JAVA.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SalaryDao salaryDao;

    @Autowired
    private CorporationDao corporationDao;

    @Autowired
    private ConventionDao conventionDao;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userDao.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userDao.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user")
    public ResponseEntity<User> create(
            @RequestBody @Valid User user){
        user.setId(null);
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        userDao.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(
            @RequestBody @Valid User userSend, @PathVariable Integer id){
        userSend.setId(id);
        Optional<User> optionalUser = userDao.findById(id);
        if(optionalUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userDao.save(userSend);
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id){
        Optional<User> optionalUser = userDao.findById(id);

        if(optionalUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userDao.deleteById(id);
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

}
