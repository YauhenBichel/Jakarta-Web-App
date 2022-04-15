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
package com.holidaysystem.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.entity.AuthorizationRoleEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * AuthorizationRole Repository implementation using java.sql.PreparedStatement
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 */
@ApplicationScoped
@Default
public class AuthorizationRoleRepository implements IAuthorizationRoleRepository {

	private static final Logger logger = Logger.getLogger(AccountRepository.class);
	
	@Resource(lookup = Constants.DATASOURCE_LOOKUP_KEY)
    DataSource dataSource;
	
	@Override
	public List<AuthorizationRoleEntity> getAll() {
		try (Connection connection = dataSource.getConnection()) {
			final String query = "SELECT id, name FROM authorization_role";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				List<AuthorizationRoleEntity> roles = new ArrayList<>();
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					AuthorizationRoleEntity authRole = new AuthorizationRoleEntity();
					authRole.setId(UUID.fromString(rs.getString("id")));
					authRole.setName(rs.getString("name"));
					
					roles.add(authRole);
				}
				
				return roles;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
	
	@Override
	public AuthorizationRoleEntity findById(UUID id) {
		try (Connection connection = dataSource.getConnection()) {
			final String query = "SELECT id, name FROM authorization_role WHERE id = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				
				stmt.setObject(1, id);
				AuthorizationRoleEntity authRole = new AuthorizationRoleEntity();
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					authRole.setId(UUID.fromString(rs.getString("id")));
					authRole.setName(rs.getString("name"));
				}
				
				return authRole;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
	
	@Override
	public AuthorizationRoleEntity findByName(String name) {
		try (Connection connection = dataSource.getConnection()) {
			final String query = "SELECT id, name FROM authorization_role WHERE name = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				
				stmt.setObject(1, name);
				AuthorizationRoleEntity authRole = new AuthorizationRoleEntity();
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					authRole.setId(UUID.fromString(rs.getString("id")));
					authRole.setName(rs.getString("name"));
				}
				
				return authRole;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
}
