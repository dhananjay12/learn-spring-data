package com.mynotes;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import com.mynotes.entity.Employee;
import com.mynotes.repository.EmployeeRepository;

@SpringBootApplication
public class Application {

	private static void basicQueries(EmployeeRepository repository) {
		
		List<Employee> empList=repository.queryUsingPositionalParameter(150000.00);
		empList.forEach(emp->System.out.println("findTopByOrderBySalaryDesc==>"+emp));
		
		empList=repository.queryUsingNamedParameter(150000.00);
		empList.forEach(emp->System.out.println("findFirstByOrderByFnameAsc==>"+emp));
		
	}
	
	private static void namedQueries(EmployeeRepository repository) {
		
		List<Employee> empList=repository.queryUsingPositionalParameter(150000.00);
		empList.forEach(emp->System.out.println("findTopByOrderBySalaryDesc==>"+emp));
		
		empList=repository.queryUsingNamedParameter(150000.00);
		empList.forEach(emp->System.out.println("findFirstByOrderByFnameAsc==>"+emp));
		
	}
	
	private static void pagingQueries(EmployeeRepository repository) {
		
		Iterable<Employee> empList= repository.findAll(new PageRequest(0, 3));
        empList.forEach((emp) -> System.out.println("First Page==>"+emp));
        
        empList= repository.findAll(new PageRequest(1, 3));
        empList.forEach((emp) -> System.out.println("Second Page==>"+emp));
        
        empList= repository.findAll(new PageRequest(2, 3));
        empList.forEach((emp) -> System.out.println("Third Page==>"+emp));
		
	}
	
	private static void pagingQueries2(EmployeeRepository repository) {
		
		List<Employee> empList= repository.findBySalaryGreaterThan(125000.00, new PageRequest(0, 3));
        empList.forEach((emp) -> System.out.println(emp));
		
	}
	
	private static void sortQueries(EmployeeRepository repository) {
		
		List<Employee> empList= repository.findAll(new Sort("fname"));
        empList.forEach((emp) -> System.out.println(emp));

        empList=repository.findAll(new Sort(Sort.Direction.DESC,"fname"));
        empList.forEach((emp) -> System.out.println(emp));
        
        empList=repository.findAll(new Sort(Sort.Direction.ASC,"fname","salary"));
        empList.forEach((emp) -> System.out.println(emp));
        
        empList=repository.findAll(new Sort(Sort.Direction.ASC,"fname").and(new Sort(Sort.Direction.DESC,"salary")));
        empList.forEach((emp) -> System.out.println(emp));
        
        empList=repository.findBySalaryGreaterThan(125000.00,new Sort(Sort.Direction.DESC,"salary"));
        empList.forEach((emp) -> System.out.println(emp));
	}
	
	private static void pagingQueries3(EmployeeRepository repository) {
		
		Page<Employee> page= repository.findAll(new PageRequest(0, 4));
		System.out.println("page.getTotalPages()==>"+page.getTotalPages());
		System.out.println("page.getNumberOfElements()==>"+page.getNumberOfElements());
		
		Slice<Employee> slice= repository.findAll(new PageRequest(0, 4));
		System.out.println("slice.getSize()==>"+slice.getSize());
		System.out.println("slice.getNumber()==>"+slice.getNumber());
		System.out.println("sslice.hasNext()==>"+slice.hasNext());
	}
	

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		EmployeeRepository repository = context.getBean(EmployeeRepository.class);
		//sortQueries(repository);
		pagingQueries3(repository);
		//pagingQueries2(repository);
		//pagingQueries(repository);
		//namedQueries(repository);
		//basicQueries(repository);
		
		
	}
}
