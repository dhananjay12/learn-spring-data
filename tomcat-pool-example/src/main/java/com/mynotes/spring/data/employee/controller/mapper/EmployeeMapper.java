package com.mynotes.spring.data.employee.controller.mapper;

import com.mynotes.spring.data.employee.dto.EmployeeDTO;
import com.mynotes.spring.data.employee.persistence.EmployeeEntity;

public class EmployeeMapper {

    public static EmployeeEntity makeEntity(EmployeeDTO employeeDTO) {
        return new EmployeeEntity(employeeDTO.getId(), employeeDTO.getFname(),
            employeeDTO.getLname(), employeeDTO.getEmail(), employeeDTO.getDob());
    }

    public static EmployeeDTO makeDTO(EmployeeEntity entity) {
        EmployeeDTO.EmployeeDTOBuilder employeeDTOBuilder = EmployeeDTO.builder()
            .id(entity.getId()).fname(entity.getFname()).lname(entity.getLname())
            .email(entity.getEmail()).dob(entity.getDob());
        return employeeDTOBuilder.build();
    }

}
