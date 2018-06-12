package com.mynotes.spring.data.advancequeries.querydsl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface EmployeeQueryDslRepository extends JpaRepository<Employee, Long>, QueryDslPredicateExecutor<Employee> {

}
