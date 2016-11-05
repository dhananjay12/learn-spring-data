package com.mynotes;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mynotes.entity.Employee;
import com.mynotes.repository.EmployeeRepository;

@SpringBootApplication
public class Application {

	private static void derivedQueries(EmployeeRepository repository) {

		Employee aEmployee=repository.findByFname("John");
		System.out.println(aEmployee);
		Iterable<Employee> empList=repository.findByLname("Doe");
		empList.forEach(emp->System.out.println(emp));
	}

	private static void stringOperatorsQueries(EmployeeRepository repository) {
		
		List<Employee> empList=repository.findByFnameLike("%oh%");
		empList.forEach(emp->System.out.println(emp));
		empList=repository.findByFnameContains("an");
		empList.forEach(emp->System.out.println(emp));
	}
	
	private static void relationalOperatorsQueries(EmployeeRepository repository) {
		
		List<Employee> empList=repository.findBySalaryEquals(150000.00);
		empList.forEach(emp->System.out.println(emp));
		empList=repository.findBySalaryGreaterThan(125000.00);
		empList.forEach(emp->System.out.println(emp));
	}
	
	private static void dateQueries(EmployeeRepository repository) {
		
		List<Employee> empList=repository.findByDobAfter(new GregorianCalendar(1990, Calendar.JANUARY, 01));
		empList.forEach(emp->System.out.println("findByDobAfter==>"+emp));
		
		empList=repository.findByDobBetween(new GregorianCalendar(1989, Calendar.JANUARY, 01),
				new GregorianCalendar(1991, Calendar.JANUARY, 01));
		empList.forEach(emp->System.out.println("findByDobBetween==>"+emp));
		
	}
	
	private static void logicalQueries(EmployeeRepository repository) {
		
		List<Employee> empList=repository.findBySalaryGreaterThanOrDobAfter(125000.00,
				new GregorianCalendar(1991, Calendar.JANUARY, 01));
		empList.forEach(emp->System.out.println("findBySalaryGreaterThanOrDobAfter==>"+emp));
		
		empList=repository.findBySalaryGreaterThanAndDobAfter(125000.00,
				new GregorianCalendar(1991, Calendar.JANUARY, 01));
		empList.forEach(emp->System.out.println("findBySalaryGreaterThanAndDobAfter==>"+emp));
		
	}
	
	private static void orderByQueries(EmployeeRepository repository) {
		
		List<Employee> empList=repository.findBySalaryGreaterThanOrderByFnameAsc(125000.00);
		empList.forEach(emp->System.out.println("findBySalaryGreaterThanOrderByFnameAsc==>"+emp));
		
		empList=repository.findBySalaryGreaterThanOrderBySalaryDesc(125000.00);
		empList.forEach(emp->System.out.println("findBySalaryGreaterThanOrderBySalaryDesc==>"+emp));
		
	}
	
	private static void limitQueries(EmployeeRepository repository) {
		
		List<Employee> empList=repository.findTopByOrderBySalaryDesc();
		empList.forEach(emp->System.out.println("findTopByOrderBySalaryDesc==>"+emp));
		
		empList=repository.findFirstByOrderByFnameAsc();
		empList.forEach(emp->System.out.println("findFirstByOrderByFnameAsc==>"+emp));
		
		empList=repository.findTop2BySalaryGreaterThanOrderBySalaryDesc(100000.00);
		empList.forEach(emp->System.out.println("findTop2BySalaryGreaterThanOrderBySalaryDesc==>"+emp));
		
	}
	

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		EmployeeRepository repository = context.getBean(EmployeeRepository.class);
		
		limitQueries(repository);
		
		//orderByQueries(repository);
		//logicalQueries(repository);
		//dateQueries(repository);
		//relationalOperatorsQueries(repository);
		//stringOperatorsQueries(repository);
		//derivedQueries(repository);
	}
}
