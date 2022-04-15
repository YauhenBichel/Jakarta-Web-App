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
	
	public AccountEntity toEntity(UUID id, RegistrationRequest registrationRequest, String hashedPassWithSalt) {
		AccountEntity account = new AccountEntity();
    	account.setId(id);
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
