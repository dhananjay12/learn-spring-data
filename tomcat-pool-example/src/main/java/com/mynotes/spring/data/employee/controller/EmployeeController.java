package com.mynotes.spring.data.employee.controller;

import com.mynotes.spring.data.employee.controller.mapper.EmployeeMapper;
import com.mynotes.spring.data.employee.dto.EmployeeDTO;
import com.mynotes.spring.data.employee.exceptions.EntityNotFoundException;
import com.mynotes.spring.data.employee.exceptions.ValidationException;
import com.mynotes.spring.data.employee.persistence.EmployeeEntity;
import com.mynotes.spring.data.employee.service.EmployeeService;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id) {
        Optional<EmployeeEntity> result = employeeService.find(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(EmployeeMapper.makeDTO(result.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public EmployeeDTO create(@Valid @RequestBody EmployeeDTO employeeDTO) {
        if(employeeDTO.getId()!=null){
            throw new ValidationException("Id should not be passed for creation");
        }
        EmployeeEntity employeeEntity = EmployeeMapper.makeEntity(employeeDTO);
        EmployeeEntity result = employeeService.create(employeeEntity);
        return EmployeeMapper.makeDTO(result);
    }

    @PutMapping("/{id}")
    public EmployeeDTO update(
        @PathVariable int id, @Valid @RequestBody EmployeeDTO employeeDTO) throws EntityNotFoundException {
        if(employeeDTO.getId()!=null && !employeeDTO.getId().equals(id)){
            throw new ValidationException("Id in path and body does not match");
        }
        EmployeeEntity result = employeeService.update(id, employeeDTO);
        return EmployeeMapper.makeDTO(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws EntityNotFoundException {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
