package com.JAVA_EVAL.JAVA.dao;

import com.JAVA_EVAL.JAVA.model.Convention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConventionDao extends JpaRepository<Convention, Integer> {
    Optional<Convention> findById(int id);
}
