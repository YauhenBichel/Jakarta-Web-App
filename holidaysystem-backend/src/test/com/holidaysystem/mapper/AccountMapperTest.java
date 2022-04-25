package com.holidaysystem.mapper;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import com.holidaysystem.common.RegistrationRequestBuilder;
import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.enumeration.AuthorizationRoleEnum;
import com.holidaysystem.mapper.AccountMapper;
import com.holidaysystem.vo.RegistrationRequest;

/**
 * Unit tests for AccountMapper class.
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 */
public class AccountMapperTest {
	
	@Test
	public void testToEntity() {
		//arrange
		UUID id = UUID.randomUUID();
		RegistrationRequest registrationRequest = 
				RegistrationRequestBuilder.builder()
				.setEmail("test.test@gmail.com")
				.setPassword("test pass !")
				.setAuthRole(AuthorizationRoleEnum.ADMIN.name())
				.build();
		
		String hashedPassWithSalt = UUID.randomUUID().toString();
		AccountMapper mapper = new AccountMapper();
		
		//act
		AccountEntity actualEntity = mapper.toEntity(id, registrationRequest, hashedPassWithSalt);
		
		//assert
		assertEquals(registrationRequest.getEmail(), actualEntity.getEmail());
		assertEquals(hashedPassWithSalt, actualEntity.getPassword());
	}
}
