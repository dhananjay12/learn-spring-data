package com.mynotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mynotes.entity.Employee;
import com.mynotes.repository.EmployeeRepository;

@SpringBootApplication
public class Application {

	

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		EmployeeRepository repository = context.getBean(EmployeeRepository.class);
		Iterable<Employee> empList = repository.findAll();
		empList.forEach((emp) -> System.out.println(emp));
	}
}
