package com.holidaysystem.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.mapper.AccountMapper;
import com.holidaysystem.vo.AccountResponse;
import com.holidaysystem.vo.LoginRequest;
import com.holidaysystem.vo.RegistrationRequest;
import com.holidaysystem.repository.AccountRepository;
import com.holidaysystem.repository.EmployeeRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * REST API for auth resource
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AuthResource {
	
	private static final Logger logger = Logger.getLogger(AuthResource.class);
	
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
    	
    	logger.debug("register()");
        
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
        
    	logger.debug("login()");
    	
    	AccountEntity accountEntity = accountRepository
    			.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
    	
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
