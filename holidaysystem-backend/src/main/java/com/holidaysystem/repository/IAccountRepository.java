package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.entity.EmployeeEntity;

public interface IAccountRepository {
	
	AccountEntity findById(UUID userId);
    
	AccountEntity findByEmail(String email);
	
	AccountEntity findByEmailAndPassword(String email, String hashedPassword); 
    
    boolean save(AccountEntity user);
}
