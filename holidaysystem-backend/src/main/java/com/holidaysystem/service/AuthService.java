package com.holidaysystem.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.transaction.Transactional;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.entity.AuthorizationRoleEntity;
import com.holidaysystem.model.AccountDetailsModel;
import com.holidaysystem.enumeration.AuthorizationRoleEnum;
import com.holidaysystem.mapper.AccountMapper;
import com.holidaysystem.repository.IAccountRepository;
import com.holidaysystem.repository.IAuthorizationRoleRepository;
import com.holidaysystem.vo.RegistrationRequest;

import javax.inject.Inject;

/**
 * Auth service provides user register and login
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
@Default
public class AuthService implements IAuthService {
	
	@Inject
	IAccountRepository accountRepository;
	@Inject
	IAuthorizationRoleRepository authRoleRepository;
	@Inject
	AccountMapper accountMapper;
	
	@Transactional
	public AccountDetailsModel login(String email, String password) {	
		AccountEntity account = accountRepository.findByEmailAndPassword(email, password);
    	
    	if(account == null)
    		throw new RuntimeException("account not found");
    	
    	AuthorizationRoleEntity authRole = authRoleRepository.findById(account.getAuthRoleId());
    	
    	AccountDetailsModel accountDetails = accountMapper.toModel(account, authRole);
    	
        return accountDetails;
	}
	
	@Transactional
	public String generateHash(String password) {
        return accountRepository.generateHashedPassword(password);
    }

	@Transactional
	public AccountDetailsModel register(RegistrationRequest registrationRequest) {
		
		final String hashedPassWithSalt = generateHash(registrationRequest.getPassword());
		
		AccountEntity account = accountMapper.toEntity(registrationRequest, hashedPassWithSalt);
		AuthorizationRoleEntity authRole = authRoleRepository.findByName(AuthorizationRoleEnum.USER.name());
		account.setAuthRoleId(authRole.getId());
		
    	accountRepository.save(account);    	
    	
    	AccountDetailsModel accountDetails = accountMapper.toModel(account, authRole);
    	
    	return accountDetails;
	}
	
}
