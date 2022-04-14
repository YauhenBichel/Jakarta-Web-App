package com.holidaysystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.model.EmployeeModel;
import com.holidaysystem.vo.NewEmployeeRequest;

/**
 * Interface for Employee service provides employee details
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IEmployeeService {
    List<EmployeeModel> getEmployees();
    
    List<EmployeeModel> getEmployeesByDate(LocalDateTime date);
    
    EmployeeModel findById(UUID employeeId);

    EmployeeModel create(NewEmployeeRequest employeeRequest);
}
