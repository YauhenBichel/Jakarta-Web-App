package com.holidaysystem.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.model.RegistrationRequest;
import com.holidaysystem.repository.AccountRepository;
import com.holidaysystem.repository.EmployeeRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.inject.Inject;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
	
	@Inject
    AccountRepository accountRepository;
	@Inject
    EmployeeRepository employeeRepository;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(RegistrationRequest registrationRequest) {
        
    	final String hashedPassWithSalt = generateHash(registrationRequest.getPassword());
    	
    	AccountEntity account = new AccountEntity();
    	account.setId(UUID.randomUUID());
    	account.setEmail(registrationRequest.getEmail());
    	account.setPassword(hashedPassWithSalt);
    	account.setCreated(LocalDateTime.now());
		account.setModified(LocalDateTime.now());
    	
    	accountRepository.save(account);
    	
    	return Response.ok(account).build();
    }
    
    private String generateHash(String password) {
        return accountRepository.generateHashedPassword(password);
    }

}
