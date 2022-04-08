package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;
import com.holidaysystem.entity.EmployeeEntity;

public interface IEmployeeRepository {
	
	EmployeeEntity findById(UUID userId);
    
    EmployeeEntity findByEmail(String email);
    
    boolean save(EmployeeEntity user);
    
    List<EmployeeEntity> getEmployees();
}
