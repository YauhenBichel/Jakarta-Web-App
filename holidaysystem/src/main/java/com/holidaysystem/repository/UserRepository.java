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

import com.holidaysystem.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserRepository implements IUserRepository {

	@Override
	public User findById(UUID userId) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String sql = "Select id, firstname, lastname, email from users where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, userId);
			
			User user = new User();
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
	public User findByEmail(String email) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String sql = "Select id, firstname, lastname, email from users where email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			
			User user = new User();
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
	public boolean save(User user) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String query = "INSERT INTO users (id, firstname, lastname, email, created, modified) "
					+ "VALUES (?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setObject(1, user.getId());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getEmail());
			ps.setString(5, LocalDateTime.now().toString());
			ps.setString(6, LocalDateTime.now().toString());
			
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
	public List<User> getUsers() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String sql = "Select id, firstname, lastname, email from users";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			List<User> users = new ArrayList<>();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(UUID.fromString(rs.getString("id")));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				
				users.add(user);
			}
			
			return users;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
