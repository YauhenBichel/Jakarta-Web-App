package com.holidaysystem.repository;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.holidaysystem.Constants;
import com.holidaysystem.DateUtils;
import com.holidaysystem.entity.EmployeeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yauhen bichel
 *
 */
@ApplicationScoped
public class EmployeeRepository implements IEmployeeRepository {

	@Override
	public EmployeeEntity findById(UUID employeeId) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "SELECT id, firstname, lastname, role, department, years, days, accountid, created, modified "
					+ "FROM employee where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, employeeId);
			
			EmployeeEntity employee = new EmployeeEntity();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				employee.setId(UUID.fromString(rs.getString("id")));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setRole(rs.getString("role"));
				employee.setDepartment(rs.getString("department"));
				employee.setYears(rs.getInt("years"));
				employee.setDays(rs.getInt("days"));
				employee.setAccountId(UUID.fromString(rs.getString("accountid")));
				employee.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
				employee.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
			}
			
			return employee;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public EmployeeEntity findByEmail(String email) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "Select id, firstname, lastname, role, department, accountid, years, days, "
					+ "created, modified from employee where email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			
			EmployeeEntity employee = new EmployeeEntity();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				employee.setId(UUID.fromString(rs.getString("id")));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setRole(rs.getString("role"));
				employee.setDepartment(rs.getString("department"));
				employee.setAccountId(UUID.fromString(rs.getString("accountid")));
				employee.setYears(rs.getInt("years"));
				employee.setDays(rs.getInt("days"));
				employee.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
				employee.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
			}
			
			return employee;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean save(EmployeeEntity employee) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String query = "INSERT INTO employee (id, firstname, lastname, role, department, accountid, "
					+ "years, days, created, modified) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setObject(1, employee.getId());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setObject(4, employee.getRole());
			ps.setObject(5, employee.getDepartment());
			ps.setObject(6, employee.getAccountId());
			ps.setObject(7, employee.getYears());
			ps.setObject(8, employee.getDays());
			ps.setObject(9, employee.getCreated());
			ps.setObject(10, employee.getModified());
			
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
	public List<EmployeeEntity> getEmployees() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "SELECT id, firstname, lastname, role, department, accountid, "
					+ "years, days, created, modified FROM employee;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			List<EmployeeEntity> employees = new ArrayList<>();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				EmployeeEntity employee = new EmployeeEntity();
				employee.setId(UUID.fromString(rs.getString("id")));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setRole(rs.getString("role"));
				employee.setDepartment(rs.getString("department"));
				employee.setAccountId(UUID.fromString(rs.getString("accountid")));
				employee.setYears(rs.getInt("years"));
				employee.setDays(rs.getInt("days"));
				employee.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
				employee.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
				
				employees.add(employee);
			}
			
			return employees;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
