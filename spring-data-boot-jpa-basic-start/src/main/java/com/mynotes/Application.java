package com.mynotes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mynotes.entity.Employee;
import com.mynotes.repository.EmployeeRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(Application.class, args);
		EmployeeRepository repository = context.getBean(EmployeeRepository.class);
		Employee emp1=new Employee();
		emp1.setFname("John");
		emp1.setLname("Doe");
		emp1.setDob(new GregorianCalendar(1990, Calendar.JULY, 3));
		emp1.setSalary(12312.00);
		emp1.setGender("M");
		
		repository.save(emp1); 
	}
}
