package com.holidaysystem.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.vo.AccountResponse;
import com.holidaysystem.vo.LoginRequest;
import com.holidaysystem.vo.RegistrationRequest;

/**
 * 
 * @author yauhen bichel
 *
 */
@ApplicationScoped
public class AccountMapper {
	
	public AccountEntity toEntity(RegistrationRequest registrationRequest, String hashedPassWithSalt) {
		AccountEntity account = new AccountEntity();
    	account.setId(UUID.randomUUID());
    	account.setEmail(registrationRequest.getEmail());
    	account.setPassword(hashedPassWithSalt);
    	account.setCreated(LocalDateTime.now());
		account.setModified(LocalDateTime.now());
    	
    	return account;
	}
	
	public AccountResponse toResponse(AccountEntity account) {
		AccountResponse resp = new AccountResponse();
    	resp.setId(account.getId());
    	resp.setEmail(account.getEmail());
    	resp.setCreated(account.getCreated().toString());
    	resp.setModified(account.getModified().toString());
		
		return resp;
	}
}
