package com.holidaysystem.security;

import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.annotation.Resource;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import com.holidaysystem.Constants;
import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.entity.AuthorizationRoleEntity;
import com.holidaysystem.model.AccountDetailsModel;
import com.holidaysystem.enumeration.AuthorizationRoleEnum;
import com.holidaysystem.repository.AccountRepository;
import com.holidaysystem.repository.AuthorizationRoleRepository;

/**
 * Authenticaiton mechanism using holidaysystem database
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
//@ApplicationScoped
public class AuthenticationMechanism implements HttpAuthenticationMechanism {
	@Resource(lookup = Constants.DATASOURCE_LOOKUP_KEY)
    private DataSource dataSource;
	
    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse,
                                                HttpMessageContext httpMessageContext) throws AuthenticationException {        
        
    	String authHeader = httpServletRequest.getHeader("Authorization");
    	
    	if(authHeader.isBlank())
    		return httpMessageContext.responseUnauthorized();
    	
    	String[] params = authHeader.split("&");
    	
    	if(params.length != 2)
    		return httpMessageContext.responseUnauthorized();
    		
        String username = params[0];
        String password = params[1];
        
        AccountDetailsModel accountDetails = findByEmailAndPassword(username, password);
        
        if (accountDetails != null) {
        	
        	Set<String> roles = new HashSet<>();
        	roles.add(accountDetails.getAuthRole().name());
        	
            return httpMessageContext.notifyContainerAboutLogin(
                    new AppPrincipal(accountDetails),
                    roles);
        }
        
        return httpMessageContext.responseUnauthorized();
    }
    
    //find why Inject does not work and fix deps
    @Transactional
    public AccountDetailsModel findByEmailAndPassword(String email, String password) {
		
    	AccountRepository accountRepository = new AccountRepository();
    	AuthorizationRoleRepository authRoleRepository = new AuthorizationRoleRepository();
    	
		final String hashedPassword = accountRepository.generateHashedPassword(password);
		
		AccountEntity account = accountRepository.findByEmailAndPassword(email, hashedPassword);
    	
    	if(account == null)
    		throw new RuntimeException("account not found");
    	
    	AuthorizationRoleEntity authRole = authRoleRepository.findById(account.getAuthRoleId());
    	
    	AccountDetailsModel accountDetails = new AccountDetailsModel();
    	accountDetails.setId(account.getId());
    	accountDetails.setEmail(account.getEmail());
    	accountDetails.setActive(account.getActive());
    	accountDetails.setAuthRole(AuthorizationRoleEnum.valueOf(authRole.getName()));
    	
        return accountDetails;
	}
}
