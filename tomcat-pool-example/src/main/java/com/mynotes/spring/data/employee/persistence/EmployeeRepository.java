package com.mynotes.spring.data.employee.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    public EmployeeEntity findByFname(String fname);


}
