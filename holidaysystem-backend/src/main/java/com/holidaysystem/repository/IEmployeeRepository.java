package com.holidaysystem.repository;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;
import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.model.EmployeeModel;

/**
 * Interface for Employee repository, which can be supported by
 * all implementations of the interface
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IEmployeeRepository {
	
	EmployeeEntity findById(UUID userId);
    
    EmployeeEntity findByEmail(String email);
    
    boolean save(EmployeeEntity user);
    
    List<EmployeeEntity> getEmployees();
    
    List<EmployeeModel> getEmployeeModels();
    
    List<EmployeeModel> getEmployeeModelsByDepartmentId(String department);
    List<EmployeeModel> getEmployeeModelsByDate(LocalDateTime date);
}
