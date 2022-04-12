package com.holidaysystem.repository;

import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.common.DateUtils;
import com.holidaysystem.entity.EmployeeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Employee Repository implementation using java.sql.PreparedStatement
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
public class EmployeeRepository implements IEmployeeRepository {

	private static final Logger logger = Logger.getLogger(EmployeeRepository.class);
	
	@Resource(lookup = Constants.DATASOURCE_LOOKUP_KEY)
    private DataSource dataSource;
	
	@Override
	public EmployeeEntity findById(UUID employeeId) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT id, firstname, lastname, role, department, years, accountid, created, modified "
					+ "FROM employee WHERE id = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
					employee.setAccountId(UUID.fromString(rs.getString("accountid")));
					employee.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					employee.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
				}
				
				return employee;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}

	@Override
	public EmployeeEntity findByEmail(String email) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT id, firstname, lastname, role, department, accountid, years, "
					+ "created, modified "
					+ "FROM employee "
					+ "WHERE email = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
					employee.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					employee.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
				}
				
				return employee;	
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}

	@Override
	public boolean save(EmployeeEntity employee) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "INSERT INTO employee (id, firstname, lastname, role, department, accountid, "
					+ "years, created, modified) "
					+ "VALUES (?,?,?,?,?,?,?,?,?);";

			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, employee.getId());
				stmt.setString(2, employee.getFirstName());
				stmt.setString(3, employee.getLastName());
				stmt.setObject(4, employee.getRole());
				stmt.setObject(5, employee.getDepartment());
				stmt.setObject(6, employee.getAccountId());
				stmt.setObject(7, employee.getYears());
				stmt.setObject(8, employee.getCreated());
				stmt.setObject(9, employee.getModified());
				
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

	@Override
	public List<EmployeeEntity> getEmployees() {
		try (Connection connection = dataSource.getConnection()) {
			final String query = "SELECT empl.id, empl.firstname, empl.lastname,"
					+ " empl.role, empl.department, empl.accountid, "
					+ "empl.years, empl.created, .modified, acc.email "
					+ "FROM employee empl;";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
					employee.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					employee.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
					
					employees.add(employee);
				}
				
				return employees;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
}
