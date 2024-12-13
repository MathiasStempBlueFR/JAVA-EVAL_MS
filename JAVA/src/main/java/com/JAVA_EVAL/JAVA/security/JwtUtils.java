package com.JAVA_EVAL.JAVA.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {
    public String generationToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .signWith(
                        SignatureAlgorithm.HS256,
                        "root")
                .compact();
    }

    public String getEmailFromJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey("root")
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
