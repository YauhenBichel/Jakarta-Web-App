package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;
import com.holidaysystem.entity.EmployeeEntity;

/**
 * 
 * @author yauhen bichel
 *
 */
public interface IEmployeeRepository {
	
	EmployeeEntity findById(UUID userId);
    
    EmployeeEntity findByEmail(String email);
    
    boolean save(EmployeeEntity user);
    
    List<EmployeeEntity> getEmployees();
}
