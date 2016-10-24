package com.mynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mynotes.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}


