package com.mynotes.spring.data.advancequeries.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mynotes.spring.data.advancequeries.common.SearchCriteria;

@RestController
@RequestMapping("/criteria")
public class EmployeeJpaCriteriaController {

    @Autowired
    private EntityManager      entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody final Employee employee) {

        employeeRepository.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employees")
    public ResponseEntity<?> searchEmployees(@RequestParam(value = "search", required = false) final String search) {
        List<Employee> employeeList = null;
        if (StringUtils.hasText(search)) {
            final List<SearchCriteria> params = new ArrayList<>();
            final Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            final Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
            employeeList = searchEmployees(params);
        } else {
            employeeList = employeeRepository.findAll();
        }

        return ResponseEntity.ok(employeeList);
    }

    private List<Employee> searchEmployees(final List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        final Root r = query.from(Employee.class);

        Predicate predicate = builder.conjunction();

        for (final SearchCriteria param : params) {
            if (param.getOperation().equalsIgnoreCase(">")) {
                predicate = builder.and(predicate,
                        builder.greaterThanOrEqualTo(r.get(param.getKey()),
                                param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase("<")) {
                predicate = builder.and(predicate,
                        builder.lessThanOrEqualTo(r.get(param.getKey()),
                                param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase(":")) {
                if (r.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.and(predicate,
                            builder.like(r.get(param.getKey()),
                                    "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.and(predicate,
                            builder.equal(r.get(param.getKey()), param.getValue()));
                }
            }
        }
        query.where(predicate);

        final List<Employee> result = entityManager.createQuery(query).getResultList();
        return result;
    }

}
