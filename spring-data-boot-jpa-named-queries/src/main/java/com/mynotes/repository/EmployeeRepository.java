package com.mynotes.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mynotes.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
   
    public List<Employee> queryUsingPositionalParameter(double amount);
   
    public List<Employee> queryUsingNamedParameter(@Param("amt") double amount);
    
    public List<Employee> findBySalaryGreaterThan(double salary, Pageable pageable);
    
    public List<Employee> findBySalaryGreaterThan(double salary, Sort sort);

}


