package com.mynotes.repository;

import org.springframework.stereotype.Repository;

import com.mynotes.entity.Employee;

@Repository
public interface EmployeeReadOnlyRepository extends ReadOnlyRepository<Employee,Integer>{

}


