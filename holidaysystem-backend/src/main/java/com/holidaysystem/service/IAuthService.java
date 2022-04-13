package com.holidaysystem.service;

import com.holidaysystem.model.AccountDetailsModel;
import com.holidaysystem.vo.RegistrationRequest;

/**
 * Interface for Auth service provides user register and login
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IAuthService {
	
	AccountDetailsModel login(String email, String password);
	
	String generateHash(String password);

	AccountDetailsModel register(RegistrationRequest registrationRequest);	
}
