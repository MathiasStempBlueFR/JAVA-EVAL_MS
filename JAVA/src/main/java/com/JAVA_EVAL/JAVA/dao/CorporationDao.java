package com.JAVA_EVAL.JAVA.dao;

import com.JAVA_EVAL.JAVA.model.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporationDao extends JpaRepository<Corporation, Integer> {
}
