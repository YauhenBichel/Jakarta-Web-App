package com.holidaysystem.service;

import java.util.List;
import java.util.UUID;

import com.holidaysystem.model.EmployeeModel;
import com.holidaysystem.vo.EmployeeRequest;

/**
 * Interface for Employee service provides employee details
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IEmployeeService {
    List<EmployeeModel> getEmployees();
    
    EmployeeModel findById(UUID employeeId);

    EmployeeModel create(EmployeeRequest employeeRequest);
}
