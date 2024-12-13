package com.JAVA_EVAL.JAVA.dao;

import com.JAVA_EVAL.JAVA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
}
