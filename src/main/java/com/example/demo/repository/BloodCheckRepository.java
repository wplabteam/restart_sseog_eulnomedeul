package com.example.demo.repository;

import com.example.demo.entity.BloodCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodCheckRepository extends JpaRepository <BloodCheck, Long>, QuerydslPredicateExecutor, BloodCheckRepositoryCustom {
}
