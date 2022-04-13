package com.holidaysystem.repository;

import java.util.UUID;

import com.holidaysystem.entity.AccountEntity;

/**
 * Interface for Account repository, which can be supported by
 * all implementations of the interface
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IAccountRepository {
	
	AccountEntity findById(UUID userId);
    
	AccountEntity findByEmail(String email);
	
	AccountEntity findByEmailAndPassword(String email, String hashedPassword); 
    
    boolean save(AccountEntity user);
    
    String generateHashedPassword(String password);
}
