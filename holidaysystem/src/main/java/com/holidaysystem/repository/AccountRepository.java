package com.holidaysystem.repository;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.entity.EmployeeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AccountRepository implements IAccountRepository {

	@Override
	public AccountEntity findById(UUID accountId) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
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
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String sql = "Select id, email, password, created, modified from account where email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			AccountEntity account = new AccountEntity();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				account.setId(UUID.fromString(rs.getString("id")));
				account.setPassword(rs.getString("password"));
				account.setEmail(rs.getString("email"));
				account.setCreated(LocalDateTime.parse(rs.getString("created"), formatter));
				account.setModified(LocalDateTime.parse(rs.getString("modified"), formatter));
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
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String sql = "Select id, email, password, created, modified from account where email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			AccountEntity account = new AccountEntity();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				account.setId(UUID.fromString(rs.getString("id")));
				account.setPassword(rs.getString("password"));
				account.setEmail(rs.getString("email"));
				account.setCreated(LocalDateTime.parse(rs.getString("created"), formatter));
				account.setModified(LocalDateTime.parse(rs.getString("modified"), formatter));
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
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String query = "INSERT INTO account (id, email, password, created, modified) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setObject(1, account.getId());
			ps.setString(2, account.getEmail());
			ps.setString(3, account.getPassword());
			ps.setObject(4, LocalDateTime.now());
			ps.setObject(5, LocalDateTime.now());
			
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
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
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
