package com.example.spring.data.employee.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class SalaryDto {
    private final Integer salary;
    private final LocalDate fromDate;
    private final LocalDate toDate;
}
