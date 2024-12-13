package com.JAVA_EVAL.JAVA.controller;

import com.JAVA_EVAL.JAVA.dao.UserDao;
import com.JAVA_EVAL.JAVA.model.Droit;
import com.JAVA_EVAL.JAVA.model.User;
import com.JAVA_EVAL.JAVA.security.AppUserDetails;
import com.JAVA_EVAL.JAVA.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class ConnexionController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @PostMapping("/sign")
    public ResponseEntity<Map<String, Object>> sign(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));

        Droit droitCorpo = new Droit();
        droitCorpo.setId(1);
        user.setDroit(droitCorpo);

        userDao.save(user);

        return ResponseEntity.ok(Map.of("message", "Enregistrement effectué"));
    }

    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody User user) {

        try {
            AppUserDetails appUserDetails = (AppUserDetails) authenticationProvider
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    user.getEmail(),
                                    user.getPassword()))
                    .getPrincipal();
            return ResponseEntity.ok(jwtUtils.generationToken(appUserDetails.getUsername()));
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}

