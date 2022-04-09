package com.holidaysystem.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.mapper.AccountMapper;
import com.holidaysystem.vo.AccountResponse;
import com.holidaysystem.vo.LoginRequest;
import com.holidaysystem.vo.RegistrationRequest;
import com.holidaysystem.repository.AccountRepository;
import com.holidaysystem.repository.EmployeeRepository;

import javax.inject.Inject;

/**
 * 
 * @author yauhen bichel
 *
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
	
	@Inject
    AccountRepository accountRepository;
	@Inject
    EmployeeRepository employeeRepository;
	@Inject
	AccountMapper accountMapper;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(RegistrationRequest registrationRequest) {
        
    	final String hashedPassWithSalt = generateHash(registrationRequest.getPassword());
		AccountEntity account = accountMapper.toEntity(registrationRequest, hashedPassWithSalt);
		
    	accountRepository.save(account);
    	
    	AccountResponse resp = accountMapper.toResponse(account);
    	
    	return Response.ok(resp)
    			.header("Access-Control-Allow-Origin", "*")
    			.status(Status.CREATED)
    			.build();
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        
    	final String hashedPassWithSalt = generateHash(loginRequest.getPassword());
    	AccountEntity accountEntity = accountRepository
    			.findByEmailAndPassword(loginRequest.getEmail(), hashedPassWithSalt);
    	
    	if(accountEntity == null) {
    		return Response.noContent()
        			.header("Access-Control-Allow-Origin", "*")
        			.status(Status.NOT_FOUND)
        			.build();
    	}
    	
    	AccountResponse resp = accountMapper.toResponse(accountEntity);
    	
    	return Response.ok(resp)
    			.header("Access-Control-Allow-Origin", "*")
    			.build();
    }
    
    private String generateHash(String password) {
        return accountRepository.generateHashedPassword(password);
    }

}
