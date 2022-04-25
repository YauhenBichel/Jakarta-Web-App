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
import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.enumeration.DepartmentEnum;
import com.holidaysystem.enumeration.EmployeeRoleEnum;
import com.holidaysystem.enumeration.HolidayStatusEnum;
import com.holidaysystem.model.EmployeeModel;

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
@Default
public class EmployeeRepository implements IEmployeeRepository {

	private static final Logger logger = Logger.getLogger(EmployeeRepository.class);
	
	@Resource(lookup = Constants.DATASOURCE_LOOKUP_KEY)
    DataSource dataSource;
	
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
					+ "empl.years, empl.created, empl.modified "
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
	
	@Override
	public List<EmployeeModel> getEmployeeModels() {
		try (Connection connection = dataSource.getConnection()) {
			final String query = "SELECT empl.id, empl.firstname, empl.lastname, " +
					"empl.role, empl.department, empl.accountid, hd.totaldays, " + 
					"hd.takendays, hd.status, empl.years, acc.email " + 
					"FROM employee empl " + 
					"inner join account acc " + 
					"on empl.accountid = acc.id " + 
					"inner join holiday_details hd " + 
					"on hd.employeeid = empl.id;";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				List<EmployeeModel> employees = new ArrayList<>();
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					EmployeeModel employee = new EmployeeModel();
					employee.setId(UUID.fromString(rs.getString("id")));
					employee.setFirstName(rs.getString("firstname"));
					employee.setLastName(rs.getString("lastname"));
					employee.setRole(EmployeeRoleEnum.valueOf(rs.getString("role")));
					employee.setDepartment(DepartmentEnum.valueOf(rs.getString("department")));
					employee.setAccountId(UUID.fromString(rs.getString("accountid")));
					employee.setEmail(rs.getString("email"));
					employee.setYears(rs.getInt("years"));
					employee.setTotalDays(rs.getInt("totaldays"));
					employee.setTakenDays(rs.getInt("takendays"));
					employee.setHolidayStatus(HolidayStatusEnum.valueOf(rs.getString("status")));
					
					employees.add(employee);
				}
				
				return employees;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
	
	@Override
	public List<EmployeeModel> getEmployeeModelsByDepartmentId(String department) {
		try (Connection connection = dataSource.getConnection()) {
			final String query = "SELECT empl.id, empl.firstname, empl.lastname, " +
					"empl.role, empl.department, empl.accountid, hd.totaldays, " + 
					"hd.takendays, hd.status, empl.years, acc.email " + 
					"FROM employee empl " + 
					"inner join account acc " + 
					"on empl.accountid = acc.id " + 
					"inner join holiday_details hd " + 
					"on hd.employeeid = empl.id "
					+ "WHERE empl.department = ?;";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, department);
				List<EmployeeModel> employees = new ArrayList<>();
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					EmployeeModel employee = new EmployeeModel();
					employee.setId(UUID.fromString(rs.getString("id")));
					employee.setFirstName(rs.getString("firstname"));
					employee.setLastName(rs.getString("lastname"));
					employee.setRole(EmployeeRoleEnum.valueOf(rs.getString("role")));
					employee.setDepartment(DepartmentEnum.valueOf(rs.getString("department")));
					employee.setAccountId(UUID.fromString(rs.getString("accountid")));
					employee.setEmail(rs.getString("email"));
					employee.setYears(rs.getInt("years"));
					employee.setTotalDays(rs.getInt("totaldays"));
					employee.setTakenDays(rs.getInt("takendays"));
					employee.setHolidayStatus(HolidayStatusEnum.valueOf(rs.getString("status")));
					
					employees.add(employee);
				}
				
				return employees;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}

	@Override
	public List<EmployeeModel> getEmployeeModelsOnHolidaysByDate(LocalDateTime date) {
		try (Connection connection = dataSource.getConnection()) {
			final String query = "SELECT empl.id, empl.firstname, empl.lastname, " +
					"empl.role, empl.department, empl.accountid, hd.totaldays, " + 
					"hd.takendays, hd.status, empl.years, acc.email " + 
					"FROM employee empl " + 
					"inner join account acc " + 
					"on empl.accountid = acc.id " + 
					"inner join holiday_details hd " + 
					"on hd.employeeid = empl.id " +
					"inner join holiday_request hr " + 
					"on hr.employeeid = empl.id " +
					"WHERE hr.startdate <= ? AND hr.enddate >= ?;";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, date);
				stmt.setObject(2, date);
				List<EmployeeModel> employees = new ArrayList<>();
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					EmployeeModel employee = new EmployeeModel();
					employee.setId(UUID.fromString(rs.getString("id")));
					employee.setFirstName(rs.getString("firstname"));
					employee.setLastName(rs.getString("lastname"));
					employee.setRole(EmployeeRoleEnum.valueOf(rs.getString("role")));
					employee.setDepartment(DepartmentEnum.valueOf(rs.getString("department")));
					employee.setAccountId(UUID.fromString(rs.getString("accountid")));
					employee.setEmail(rs.getString("email"));
					employee.setYears(rs.getInt("years"));
					employee.setTotalDays(rs.getInt("totaldays"));
					employee.setTakenDays(rs.getInt("takendays"));
					employee.setHolidayStatus(HolidayStatusEnum.valueOf(rs.getString("status")));
					
					employees.add(employee);
				}
				
				return employees;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
	
	@Override
	public List<EmployeeModel> getEmployeeModelsByDateAndHolidayStatus(LocalDateTime date, HolidayStatusEnum holidayStatus) {
		try (Connection connection = dataSource.getConnection()) {
			final String query = "SELECT empl.id, empl.firstname, empl.lastname, " +
					"empl.role, empl.department, empl.accountid, hd.totaldays, " + 
					"hd.takendays, hd.status, empl.years, acc.email " + 
					"FROM employee empl " + 
					"inner join account acc " + 
					"on empl.accountid = acc.id " + 
					"inner join holiday_details hd " + 
					"on hd.employeeid = empl.id " +
					"inner join holiday_request hr " + 
					"on hr.employeeid = empl.id " +
					"WHERE hr.startdate <= ? AND hr.enddate >= ? AND "
					+ "hd.status = ?;";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, date);
				stmt.setObject(2, date);
				stmt.setObject(3, holidayStatus.name());
				List<EmployeeModel> employees = new ArrayList<>();
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					EmployeeModel employee = new EmployeeModel();
					employee.setId(UUID.fromString(rs.getString("id")));
					employee.setFirstName(rs.getString("firstname"));
					employee.setLastName(rs.getString("lastname"));
					employee.setRole(EmployeeRoleEnum.valueOf(rs.getString("role")));
					employee.setDepartment(DepartmentEnum.valueOf(rs.getString("department")));
					employee.setAccountId(UUID.fromString(rs.getString("accountid")));
					employee.setEmail(rs.getString("email"));
					employee.setYears(rs.getInt("years"));
					employee.setTotalDays(rs.getInt("totaldays"));
					employee.setTakenDays(rs.getInt("takendays"));
					employee.setHolidayStatus(HolidayStatusEnum.valueOf(rs.getString("status")));
					
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
