package com.holidaysystem.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.entity.AuthorizationRoleEntity;
import com.holidaysystem.enumeration.AuthorizationRoleEnum;
import com.holidaysystem.model.AccountDetailsModel;
import com.holidaysystem.vo.AccountResponse;
import com.holidaysystem.vo.RegistrationRequest;

/**
 * Account mapper
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
public class AccountMapper {
	
	public AccountEntity toEntity(RegistrationRequest registrationRequest, String hashedPassWithSalt) {
		AccountEntity account = new AccountEntity();
    	account.setId(UUID.randomUUID());
    	account.setEmail(registrationRequest.getEmail());
    	account.setPassword(hashedPassWithSalt);
    	account.setActive(true);
    	account.setCreated(LocalDateTime.now());
		account.setModified(LocalDateTime.now());
    	
    	return account;
	}
	
	public AccountResponse toResponse(AccountDetailsModel model) {
		AccountResponse resp = new AccountResponse();
    	resp.setId(model.getId());
    	resp.setEmail(model.getEmail());
    	resp.setActive(model.getActive());
    	resp.setAuthRole(model.getAuthRole().name());
		
		return resp;
	}
	
	public AccountDetailsModel toModel(AccountEntity account, AuthorizationRoleEntity authRole) {
		AccountDetailsModel accountDetails = new AccountDetailsModel();
    	accountDetails.setId(account.getId());
    	accountDetails.setEmail(account.getEmail());
    	accountDetails.setActive(account.getActive());
    	accountDetails.setAuthRole(AuthorizationRoleEnum.valueOf(authRole.getName()));
    	
    	return accountDetails;
	}
}
