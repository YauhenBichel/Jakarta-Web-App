/*
 *     Copyright 2022-2022 Yauhen Bichel yb3129h@gre.ac.uk OR bichel.eugen@gmail.com 
 *     Student Id 001185491
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.holidaysystem.service;

import java.util.UUID;

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
		
		AccountEntity dbEntity = accountRepository.findByEmail(registrationRequest.getEmail());
		if(dbEntity != null)
			throw new RuntimeException("duplicate");
		
		final UUID id = UUID.randomUUID();
		final String hashedPassWithSalt = generateHash(registrationRequest.getPassword());
		
		AccountEntity account = accountMapper.toEntity(id, registrationRequest, hashedPassWithSalt);
		AuthorizationRoleEntity authRole = authRoleRepository.findByName(AuthorizationRoleEnum.USER.name());
		account.setAuthRoleId(authRole.getId());
		
    	accountRepository.save(account);    	
    	
    	AccountDetailsModel accountDetails = accountMapper.toModel(account, authRole);
    	
    	return accountDetails;
	}
	
}
