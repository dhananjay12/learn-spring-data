package com.mynotes.spring.data.advancequeries.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.mynotes.spring.data.advancequeries.common.SearchCriteria;

public class EmployeeSearchSpecificationBuilder {

    private final List<SearchCriteria> params;

    public EmployeeSearchSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public EmployeeSearchSpecificationBuilder with(final String key, final String operation, final Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Employee> build() {
        if (params.size() == 0) {
            return null;
        }

        final List<Specification<Employee>> specs = new ArrayList<>();
        for (final SearchCriteria param : params) {
            specs.add(new EmployeeSearchSpecification(param));
        }

        Specification<Employee> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }

}
