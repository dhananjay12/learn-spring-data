package com.mynotes.spring.data.employee.service;

import com.mynotes.spring.data.employee.dto.EmployeeDTO;
import com.mynotes.spring.data.employee.exceptions.EntityNotFoundException;
import com.mynotes.spring.data.employee.persistence.EmployeeEntity;
import com.mynotes.spring.data.employee.persistence.EmployeeRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeEntity> find(int id) {
        return employeeRepository.findById(id);
    }

    public EmployeeEntity create(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity update(int id, EmployeeDTO employeeDTO) throws EntityNotFoundException {

        EmployeeEntity employeeEntity = findEmployeeById(id);
        employeeEntity.setFname(employeeDTO.getFname());
        employeeEntity.setLname(employeeDTO.getLname());
        employeeEntity.setEmail(employeeDTO.getEmail());
        return employeeRepository.save(employeeEntity);
    }

    public void delete(int id) throws EntityNotFoundException {
        EmployeeEntity result = findEmployeeById(id);
        employeeRepository.deleteById(id);
    }


    private EmployeeEntity findEmployeeById(Integer id) throws EntityNotFoundException {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + id));
    }
}
