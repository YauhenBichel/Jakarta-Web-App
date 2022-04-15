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

import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.common.DateUtils;
import com.holidaysystem.entity.AccountEntity;

import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * Account Repository implementation using java.sql.PreparedStatement
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 */
@ApplicationScoped
@Default
public class AccountRepository implements IAccountRepository {

	private static final Logger logger = Logger.getLogger(AccountRepository.class);
	
	@Resource(lookup = Constants.DATASOURCE_LOOKUP_KEY)
    DataSource dataSource;
	
	@Override
	public AccountEntity findById(UUID accountId) {
		try (Connection connection = dataSource.getConnection()) {
			final String query = "SELECT acc.id, acc.email, acc.password, acc.active, acc.auth_roleid, acc.created, acc.modified "
					+ "FROM account acc "
					+ "WHERE id = ?;"; 
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, accountId);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					AccountEntity account = new AccountEntity();
					account.setId(UUID.fromString(rs.getString("id")));
					account.setPassword(rs.getString("password"));
					account.setEmail(rs.getString("email"));
					account.setActive(rs.getBoolean("active"));
					account.setAuthRoleId(UUID.fromString(rs.getString("auth_roleid")));
					account.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					account.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
					
					return account;
				}
				
				return null;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}

	@Override
	public AccountEntity findByEmail(String email) {
		
		try (Connection connection = dataSource.getConnection()) {
		
			String query = "SELECT acc.id, acc.email, acc.password, acc.active, acc.auth_roleid, acc.created, acc.modified "
					+ "FROM account acc "
					+ "WHERE acc.email = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, email);
				
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					AccountEntity account = new AccountEntity();
					account.setId(UUID.fromString(rs.getString("id")));
					account.setPassword(rs.getString("password"));
					account.setEmail(rs.getString("email"));
					account.setActive(rs.getBoolean("active"));
					account.setAuthRoleId(UUID.fromString(rs.getString("auth_roleid")));
					account.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					account.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
					
					return account;
				}
				
				return null;	
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
	
	@Override
	public AccountEntity findByEmailAndPassword(String email, String password) {		
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT acc.id, acc.email, acc.password, acc.active, acc.auth_roleid, acc.created, acc.modified "
					+ "FROM account acc "
					+ "WHERE acc.email = ? AND "
					+ "crypt(?, acc.password) = acc.password";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, email);
				stmt.setString(2, password);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					AccountEntity account = new AccountEntity();
					account.setId(UUID.fromString(rs.getString("id")));
					account.setPassword(rs.getString("password"));
					account.setEmail(rs.getString("email"));
					account.setActive(rs.getBoolean("active"));
					account.setAuthRoleId(UUID.fromString(rs.getString("auth_roleid")));
					account.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					account.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
					
					return account;
				}
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}

	@Override
	public boolean save(AccountEntity account) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "INSERT INTO account (id, email, password, active, auth_roleid, created, modified) "
					+ "VALUES (?,?,?,?,?,?,?);";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, account.getId());
				stmt.setString(2, account.getEmail());
				stmt.setString(3, account.getPassword());
				stmt.setObject(4, account.getActive());
				stmt.setObject(5, account.getAuthRoleId());
				stmt.setObject(6, account.getCreated());
				stmt.setObject(7, account.getModified());
				
				if (stmt.executeUpdate() == 1) {
				     return true;
				} else {
				     return false;
				}
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return false;
	}

    public String generateHashedPassword(String password) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT crypt(?, gen_salt('bf', 8))";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, password);
				
				ResultSet rs = stmt.executeQuery();
				
				String hashedPass = "";
				
				while(rs.next()) {
					hashedPass = rs.getString(1);
				}
				
				return hashedPass;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
}
