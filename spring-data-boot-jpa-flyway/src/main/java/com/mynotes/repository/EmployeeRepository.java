package com.mynotes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mynotes.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer>{

}


