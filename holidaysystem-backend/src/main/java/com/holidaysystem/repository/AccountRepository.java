package com.holidaysystem.repository;

import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.common.DateUtils;
import com.holidaysystem.entity.AccountEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * Account Repository implementation using java.sql.PreparedStatement
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 */
@ApplicationScoped
public class AccountRepository implements IAccountRepository {

	private static final Logger logger = Logger.getLogger(AccountRepository.class);
	
	@Resource(lookup = Constants.DATASOURCE_LOOKUP_KEY)
    private DataSource dataSource;
	
	@Override
	public AccountEntity findById(UUID accountId) {
		try (Connection connection = dataSource.getConnection()) {
			final String query = "SELECT id, password, email FROM account WHERE id = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, accountId);
				AccountEntity account = new AccountEntity();
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					account.setId(UUID.fromString(rs.getString("id")));
					account.setPassword(rs.getString("password"));
					account.setEmail(rs.getString("email"));
				}
				
				return account;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}

	@Override
	public AccountEntity findByEmail(String email) {
		
		try (Connection connection = dataSource.getConnection()) {
		
			String query = "SELECT id, email, password, created, modified "
					+ "FROM account "
					+ "WHERE email = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, email);
				
				AccountEntity account = new AccountEntity();
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					account.setId(UUID.fromString(rs.getString("id")));
					account.setPassword(rs.getString("password"));
					account.setEmail(rs.getString("email"));
					account.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					account.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
				}
				
				return account;	
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
	
	@Override
	public AccountEntity findByEmailAndPassword(String email, String password) {		
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT acc.id, acc.email, acc.password, acc.created, acc.modified "
					+ "FROM account acc "
					+ "WHERE acc.email = ? AND "
					+ "crypt(?, acc.password) = acc.password";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, email);
				stmt.setString(2, password);
				
				AccountEntity account = new AccountEntity();
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					account.setId(UUID.fromString(rs.getString("id")));
					account.setPassword(rs.getString("password"));
					account.setEmail(rs.getString("email"));
					account.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					account.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
				}
				
				return account;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}

	@Override
	public boolean save(AccountEntity account) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "INSERT INTO account (id, email, password, created, modified) "
					+ "VALUES (?,?,?,?,?);";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, account.getId());
				stmt.setString(2, account.getEmail());
				stmt.setString(3, account.getPassword());
				stmt.setObject(4, account.getCreated());
				stmt.setObject(5, account.getModified());
				
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
