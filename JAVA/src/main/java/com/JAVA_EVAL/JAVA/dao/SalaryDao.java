package com.JAVA_EVAL.JAVA.dao;

import com.JAVA_EVAL.JAVA.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryDao extends JpaRepository<Salary, Integer> {
    Optional<Salary> findById(Integer integer);
}
