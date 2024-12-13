package com.JAVA_EVAL.JAVA.dao;

import com.JAVA_EVAL.JAVA.model.Corporation;
import com.JAVA_EVAL.JAVA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorporationDao extends JpaRepository<Corporation, Integer> {
    Optional<Corporation> findByName(String name);
}
