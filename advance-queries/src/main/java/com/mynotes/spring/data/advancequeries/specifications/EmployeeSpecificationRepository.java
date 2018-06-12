package com.mynotes.spring.data.advancequeries.specifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeSpecificationRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

}
