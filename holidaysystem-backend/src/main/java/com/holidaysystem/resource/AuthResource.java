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
package com.holidaysystem.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.holidaysystem.mapper.AccountMapper;
import com.holidaysystem.model.AccountDetailsModel;
import com.holidaysystem.vo.AccountResponse;
import com.holidaysystem.vo.LoginRequest;
import com.holidaysystem.vo.RegistrationRequest;
import com.holidaysystem.service.IAuthService;

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
	private IAuthService authService;
	@Inject
	private AccountMapper accountMapper;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(RegistrationRequest registrationRequest) {
    	
    	logger.debug("register");
        
    	AccountDetailsModel accountModel = authService
    			.register(registrationRequest);
    	
    	AccountResponse resp = accountMapper.toResponse(accountModel);
    	
    	return Response.ok(resp)
    			.header("Access-Control-Allow-Origin", "*")
    			.status(Status.CREATED)
    			.build();
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
    	
    	AccountDetailsModel accountModel = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    	
    	if(accountModel == null) {
    		logger.info("account not found");
    		
    		return Response.noContent()
        			.header("Access-Control-Allow-Origin", "*")
        			.status(Status.NOT_FOUND)
        			.build();
    	}
    	
    	AccountResponse resp = accountMapper.toResponse(accountModel);
    	
    	return Response.ok(resp)
    			.header("Access-Control-Allow-Origin", "*")
    			.build();
    }
}
