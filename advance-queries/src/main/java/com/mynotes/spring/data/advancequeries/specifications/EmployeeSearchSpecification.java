package com.mynotes.spring.data.advancequeries.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mynotes.spring.data.advancequeries.common.SearchCriteria;

public class EmployeeSearchSpecification implements Specification<Employee> {

    private final SearchCriteria criteria;

    public EmployeeSearchSpecification(final SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(final Root<Employee> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return cb.greaterThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return cb.lessThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return cb.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return cb.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }

}
