package com.mynotes.spring.data.advancequeries.querydsl;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

@RestController
@RequestMapping("dsl")
public class EmployeeJpaQueryDslController {

    @Autowired
    private EmployeeQueryDslRepository employeeQueryDslRepository;

    @RequestMapping(method = RequestMethod.POST, value = "employee")
    public ResponseEntity<?> saveEmployee(@RequestBody final Employee employee) {

        employeeQueryDslRepository.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "employees")
    public ResponseEntity<?> searchEmployees(@RequestParam(value = "search", required = false) final String search) {
        Iterable<Employee> employeeList = new ArrayList<>();
        final EmployeePredicateBuilder builder = new EmployeePredicateBuilder();
        if (StringUtils.hasText(search)) {
            final Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            final Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            final BooleanExpression exp = builder.build();
            employeeList = employeeQueryDslRepository.findAll(exp);
        }else {
        	employeeList = employeeQueryDslRepository.findAll();
        }
        return ResponseEntity.ok(employeeList);
    }

}
