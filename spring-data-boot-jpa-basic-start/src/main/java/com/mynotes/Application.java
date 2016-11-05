package com.mynotes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mynotes.entity.Employee;
import com.mynotes.repository.EmployeeReadOnlyRepository;
import com.mynotes.repository.EmployeeRepository;

@SpringBootApplication
public class Application {

	private static void selectQueries(EmployeeRepository repository) {

		/*
		 * Iterable<Employee> empList = repository.findAll();
		 * empList.forEach((emp)->System.out.println(emp));
		 */

		/*
		 * Employee aEmployee=repository.findOne(2);
		 * System.out.println(aEmployee);
		 */

		Iterable<Employee> empList = repository.findAll(new ArrayList<Integer>() {
			{
				add(1);
				add(3);
			}
		});
		empList.forEach((emp) -> System.out.println(emp));
	}

	private static void saveQueries(EmployeeRepository repository) {

		Employee emp1 = new Employee();
		emp1.setFname("Max");
		emp1.setLname("Payne");
		emp1.setDob(new GregorianCalendar(1987, Calendar.APRIL, 3));
		emp1.setSalary(150000.00);
		emp1.setGender("M");

		repository.save(emp1);
	}
	
	private static void modifyQueries(EmployeeRepository repository) {

		 Employee aEmployee=repository.findOne(4);
		 System.out.println(aEmployee.getSalary());
		 aEmployee.setSalary(140000.00);
		 repository.save(aEmployee);
		 System.out.println(aEmployee.getSalary());
	}
	
	private static void deleteQueries(EmployeeRepository repository) {

		repository.delete(4);
		 System.out.println(repository.findOne(4));
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		EmployeeReadOnlyRepository repository = context.getBean(EmployeeReadOnlyRepository.class);
		//repository.delete(4);
		
		//EmployeeRepository repository = context.getBean(EmployeeRepository.class);
		//modifyQueries(repository);
		
		//saveQueries(repository);
		

		// selectQueries(repository);
		//deleteQueries(repository);
	}
}
