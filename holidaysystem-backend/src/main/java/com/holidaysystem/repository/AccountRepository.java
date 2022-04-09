package com.holidaysystem.repository;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.holidaysystem.Constants;
import com.holidaysystem.DateUtils;
import com.holidaysystem.entity.AccountEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author yauhen bichel
 *
 */
@ApplicationScoped
public class AccountRepository implements IAccountRepository {

	@Override
	public AccountEntity findById(UUID accountId) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "Select id, password, email from account where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
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
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public AccountEntity findByEmail(String email) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "Select id, email, password, created, modified from account where email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
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
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public AccountEntity findByEmailAndPassword(String email, String hashedPassword) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "SELECT id, email, password, created, modified "
					+ "FROM account "
					+ "WHERE email = ? AND password = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, hashedPassword);
			
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
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean save(AccountEntity account) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String query = "INSERT INTO account (id, email, password, created, modified) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(query);
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
			ex.printStackTrace();
		}
		
		return false;
	}

    public String generateHashedPassword(String password) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "SELECT crypt(?, gen_salt('bf', 8))";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, password);
			
			ResultSet rs = stmt.executeQuery();
			
			String hashedPass = "";
			
			while(rs.next()) {
				hashedPass = rs.getString(1);
			}
			
			return hashedPass;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
