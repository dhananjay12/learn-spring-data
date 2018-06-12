package com.mynotes.spring.data.advancequeries.querydsl;

import java.util.ArrayList;
import java.util.List;

import com.mynotes.spring.data.advancequeries.common.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;

public class EmployeePredicateBuilder {

    private final List<SearchCriteria> params;

    public EmployeePredicateBuilder() {
        params = new ArrayList<>();
    }

    public EmployeePredicateBuilder with(final String key, final String operation, final Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.size() == 0) {
            return null;
        }

        final List<BooleanExpression> predicates = new ArrayList<>();
        EmployeePredicate predicate;
        for (final SearchCriteria param : params) {
            predicate = new EmployeePredicate(param);
            final BooleanExpression exp = predicate.getPredicate();
            if (exp != null) {
                predicates.add(exp);
            }
        }

        BooleanExpression result = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }
        return result;
    }

}
