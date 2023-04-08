package com.example.spring.data.employee.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EmployeeSalaryDto {
    private final String firstName;
    private final String lastName;
    private final List<SalaryDto> salaries;
}
