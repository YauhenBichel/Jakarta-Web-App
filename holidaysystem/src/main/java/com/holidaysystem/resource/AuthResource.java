package com.holidaysystem.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.model.RegistrationRequest;
import com.holidaysystem.repository.AccountRepository;
import com.holidaysystem.repository.EmployeeRepository;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static javax.ws.rs.core.Response.status;

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
    	System.out.println(hashedPassWithSalt);
    	
    	AccountEntity account = new AccountEntity();
    	account.setId(UUID.randomUUID());
    	account.setEmail(registrationRequest.getEmail());
    	account.setPassword(hashedPassWithSalt);
    	
    	accountRepository.save(account);
    	
    	return Response.ok(account).build();
    }
    
    private String generateHash(String password) {
        return accountRepository.generateHashedPassword(password);
    }

}
