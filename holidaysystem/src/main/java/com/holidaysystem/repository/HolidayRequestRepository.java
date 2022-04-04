package com.holidaysystem.repository;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.holidaysystem.entity.HolidayRequestEntity;

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
public class HolidayRequestRepository implements IHolidayRequestRepository {

	private static final String BASE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final DateTimeFormatter FORMATTER = 
		    new DateTimeFormatterBuilder().appendPattern(BASE_PATTERN)
		        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();
	
	@Override
	public HolidayRequestEntity findById(UUID holidayRequestId) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String sql = "SELECT id, employeeid, status, startdate, enddate, created, modified FROM holiday_request WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, holidayRequestId);
			
			HolidayRequestEntity entity = new HolidayRequestEntity();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				entity.setId(UUID.fromString(rs.getString("id")));
				entity.setEmployeeId(UUID.fromString(rs.getString("employeeid")));
				entity.setStatus(rs.getString("status"));
				entity.setStartDate(LocalDateTime.parse(rs.getString("startdate"), FORMATTER));
				entity.setEndDate(LocalDateTime.parse(rs.getString("enddate"), FORMATTER));
				entity.setCreated(LocalDateTime.parse(rs.getString("created"), FORMATTER));
				entity.setModified(LocalDateTime.parse(rs.getString("modified"), FORMATTER));
			}
			
			return entity;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	
	@Override
	public boolean save(HolidayRequestEntity holidayRequestEntity) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String query = "INSERT INTO holiday_request (id, employeeid, status, startdate, enddate, created, modified) "
					+ "VALUES (?,?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setObject(1, holidayRequestEntity.getId());
			ps.setObject(2, holidayRequestEntity.getEmployeeId());
			ps.setString(3, holidayRequestEntity.getStatus());
			ps.setObject(4, holidayRequestEntity.getStartDate());
			ps.setObject(5, holidayRequestEntity.getEndDate());
			ps.setObject(6, holidayRequestEntity.getCreated());
			ps.setObject(7, holidayRequestEntity.getModified());
			
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
	public List<HolidayRequestEntity> getHolidayRequests() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String sql = "SELECT id, employeeid, status, startdate, enddate, created, modified FROM holiday_request";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			List<HolidayRequestEntity> holidayRequests = new ArrayList<>();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				HolidayRequestEntity entity = new HolidayRequestEntity();
				entity.setId(UUID.fromString(rs.getString("id")));
				entity.setEmployeeId(UUID.fromString(rs.getString("employeeid")));
				entity.setStatus(rs.getString("status"));
				entity.setStartDate(LocalDateTime.parse(rs.getString("startdate"), FORMATTER));
				entity.setEndDate(LocalDateTime.parse(rs.getString("enddate"), FORMATTER));
				entity.setCreated(LocalDateTime.parse(rs.getString("created"), FORMATTER));
				entity.setModified(LocalDateTime.parse(rs.getString("modified"), FORMATTER));
				
				holidayRequests.add(entity);
			}
			
			return holidayRequests;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}


	@Override
	public HolidayRequestEntity update(UUID id, HolidayRequestEntity holidayRequestEntity) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			Connection conn = ds.getConnection();
			
			String query = "UPDATE holiday_request SET status = ?, startdate = ?, enddate = ?, modified = ?, employeeid = ? where id= ?;";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, holidayRequestEntity.getStatus());
			ps.setObject(2, holidayRequestEntity.getStartDate());
			ps.setObject(3, holidayRequestEntity.getEndDate());
			ps.setObject(4, holidayRequestEntity.getModified());
			ps.setObject(5, holidayRequestEntity.getEmployeeId());
			ps.setObject(6, id);
			
			if (ps.executeUpdate() == 1) {
			     return holidayRequestEntity;
			} else {
			     return null;
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	
}
