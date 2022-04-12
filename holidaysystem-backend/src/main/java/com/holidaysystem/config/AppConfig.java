package com.holidaysystem.config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/**
 * Application configuration
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@BasicAuthenticationMechanismDefinition(realmName = "defaultRealm")
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:comp/env/jdbc/securityDS",
        callerQuery = "select password from users where username = ?",
        groupsQuery = "select GROUPNAME from groups where username = ?"
)
@ApplicationScoped
public class AppConfig {
	
}
