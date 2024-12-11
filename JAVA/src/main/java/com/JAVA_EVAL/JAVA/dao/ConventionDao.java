package com.JAVA_EVAL.JAVA.dao;

import com.JAVA_EVAL.JAVA.model.Convention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventionDao extends JpaRepository<Convention, Integer> {
}
