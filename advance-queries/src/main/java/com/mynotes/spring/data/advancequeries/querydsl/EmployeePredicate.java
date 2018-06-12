package com.mynotes.spring.data.advancequeries.querydsl;

import com.mynotes.spring.data.advancequeries.common.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class EmployeePredicate {

    private final SearchCriteria criteria;

    public EmployeePredicate(final SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public BooleanExpression getPredicate() {
        final PathBuilder<Employee> entityPath = new PathBuilder<>(Employee.class, "employee");

        if (isNumeric(criteria.getValue().toString())) {
            final NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            final int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case ":":
                    return path.eq(value);
                case ">":
                    return path.goe(value);
                case "<":
                    return path.loe(value);
            }
        } else {
            final StringPath path = entityPath.getString(criteria.getKey());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
    }

    public static boolean isNumeric(final String str) {
        try {
            Integer.parseInt(str);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

}
