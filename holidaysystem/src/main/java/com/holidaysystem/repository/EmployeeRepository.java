package com.holidaysystem.repository;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.holidaysystem.entity.EmployeeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeRepository implements IEmployeeRepository {

	private static final String BASE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final DateTimeFormatter FORMATTER = 
		    new DateTimeFormatterBuilder().appendPattern(BASE_PATTERN)
		        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();
	
	@Override
	public EmployeeEntity findById(UUID userId) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String sql = "SELECT id, firstname, lastname, email FROM employee where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, userId);
			
			EmployeeEntity user = new EmployeeEntity();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				user.setId(UUID.fromString(rs.getString("id")));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
			}
			
			return user;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public EmployeeEntity findByEmail(String email) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String sql = "Select id, firstname, lastname, email, created, modified from employee where email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			
			EmployeeEntity user = new EmployeeEntity();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				user.setId(UUID.fromString(rs.getString("id")));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setCreated(LocalDateTime.parse(rs.getString("created"), FORMATTER));
				user.setModified(LocalDateTime.parse(rs.getString("modified"), FORMATTER));
			}
			
			return user;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean save(EmployeeEntity user) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String query = "INSERT INTO user (id, firstname, lastname, email, created, modified) "
					+ "VALUES (?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setObject(1, user.getId());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getEmail());
			ps.setObject(5, LocalDateTime.now());
			ps.setObject(6, LocalDateTime.now());
			
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

	@Override
	public List<EmployeeEntity> getUsers() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String sql = "SELECT u.id, u.firstname, u.lastname, u.email, u.accountid, u.created, u.modified FROM employee u;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			List<EmployeeEntity> users = new ArrayList<>();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				EmployeeEntity user = new EmployeeEntity();
				user.setId(UUID.fromString(rs.getString("id")));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				//user.setAccountId(UUID.fromString(rs.getString("accountid")));
				user.setCreated(LocalDateTime.parse(rs.getString("created"), FORMATTER));
				user.setModified(LocalDateTime.parse(rs.getString("modified"), FORMATTER));
				
				users.add(user);
			}
			
			return users;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
