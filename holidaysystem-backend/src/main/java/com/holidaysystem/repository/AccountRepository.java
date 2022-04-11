package com.holidaysystem.repository;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.common.DateUtils;
import com.holidaysystem.entity.AccountEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Account Repository implementation using java.sql.PreparedStatement
 * @author yauhen bichel
 */
@ApplicationScoped
public class AccountRepository implements IAccountRepository {

	private static final Logger logger = Logger.getLogger(AccountRepository.class);
	
	@Override
	public AccountEntity findById(UUID accountId) {
		InitialContext initialContext = null;
		Connection dsConnection = null;
		
		try {
			
			initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			dsConnection = ds.getConnection();
			
			String sql = "Select id, password, email from account where id = ?";
			PreparedStatement stmt = dsConnection.prepareStatement(sql);
			stmt.setObject(1, accountId);
			
			AccountEntity account = new AccountEntity();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				account.setId(UUID.fromString(rs.getString("id")));
				account.setPassword(rs.getString("password"));
				account.setEmail(rs.getString("email"));
			}
			
			return account;
			
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			try {
				if(dsConnection != null)
					dsConnection.close();
				if(initialContext != null)
					initialContext.close();
			}catch (SQLException ex) {
				logger.error(ex.getMessage(), ex);
			}catch (NamingException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		
		return null;
	}

	@Override
	public AccountEntity findByEmail(String email) {
		
		InitialContext initialContext = null;
		Connection dsConnection = null;
		
		try {
			
			initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			dsConnection = ds.getConnection();
			
			String sql = "Select id, email, password, created, modified from account where email = ?";
			PreparedStatement stmt = dsConnection.prepareStatement(sql);
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
			
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			try {
				if(dsConnection != null)
					dsConnection.close();
				if(initialContext != null)
					initialContext.close();
			}catch (SQLException ex) {
				logger.error(ex.getMessage(), ex);
			}catch (NamingException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		
		return null;
	}
	
	@Override
	public AccountEntity findByEmailAndPassword(String email, String password) {
		InitialContext initialContext = null;
		Connection dsConnection = null;
		
		try {
			
			initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			dsConnection = ds.getConnection();
			
			String sql = "SELECT acc.id, acc.email, acc.password, acc.created, acc.modified "
					+ "FROM account acc "
					+ "WHERE acc.email = ? AND "
					+ "crypt(?, acc.password) = acc.password";
			PreparedStatement stmt = dsConnection.prepareStatement(sql);
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
			
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			try {
				if(dsConnection != null)
					dsConnection.close();
				if(initialContext != null)
					initialContext.close();
			}catch (SQLException ex) {
				logger.error(ex.getMessage(), ex);
			}catch (NamingException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		
		return null;
	}

	@Override
	public boolean save(AccountEntity account) {
		InitialContext initialContext = null;
		Connection dsConnection = null;
		
		try {
			
			initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			dsConnection = ds.getConnection();
			
			String query = "INSERT INTO account (id, email, password, created, modified) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement ps = dsConnection.prepareStatement(query);
			ps.setObject(1, account.getId());
			ps.setString(2, account.getEmail());
			ps.setString(3, account.getPassword());
			ps.setObject(4, account.getCreated());
			ps.setObject(5, account.getModified());
			
			if (ps.executeUpdate() == 1) {
			     return true;
			} else {
			     return false;
			}
			
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			try {
				if(dsConnection != null)
					dsConnection.close();
				if(initialContext != null)
					initialContext.close();
			}catch (SQLException ex) {
				logger.error(ex.getMessage(), ex);
			}catch (NamingException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		
		return false;
	}

    public String generateHashedPassword(String password) {
    	InitialContext initialContext = null;
		Connection dsConnection = null;
		
		try {
			
			initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			dsConnection = ds.getConnection();
			
			String sql = "SELECT crypt(?, gen_salt('bf', 8))";
			PreparedStatement stmt = dsConnection.prepareStatement(sql);
			stmt.setObject(1, password);
			
			ResultSet rs = stmt.executeQuery();
			
			String hashedPass = "";
			
			while(rs.next()) {
				hashedPass = rs.getString(1);
			}
			
			return hashedPass;
			
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			try {
				if(dsConnection != null)
					dsConnection.close();
				if(initialContext != null)
					initialContext.close();
			}catch (SQLException ex) {
				logger.error(ex.getMessage(), ex);
			}catch (NamingException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		
		return null;
	}
}
