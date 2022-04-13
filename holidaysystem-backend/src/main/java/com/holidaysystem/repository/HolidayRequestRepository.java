package com.holidaysystem.repository;

import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.common.DateUtils;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * HolidayRequest Repository implementation using java.sql.PreparedStatement
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
@Default
public class HolidayRequestRepository implements IHolidayRequestRepository {

	private static final Logger logger = Logger.getLogger(HolidayRequestRepository.class);
	
	@Resource(lookup = Constants.DATASOURCE_LOOKUP_KEY)
    private DataSource dataSource;
	
	@Override
	public HolidayRequestEntity findById(UUID holidayRequestId) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT id, employeeid, status, startdate, enddate, created, modified "
					+ "FROM holiday_request "
					+ "WHERE id = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, holidayRequestId);
				
				HolidayRequestEntity entity = new HolidayRequestEntity();
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					entity.setId(UUID.fromString(rs.getString("id")));
					entity.setEmployeeId(UUID.fromString(rs.getString("employeeid")));
					entity.setStatus(rs.getString("status"));
					entity.setStartDate(LocalDateTime.parse(rs.getString("startdate"), DateUtils.FORMATTER));
					entity.setEndDate(LocalDateTime.parse(rs.getString("enddate"), DateUtils.FORMATTER));
					entity.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					entity.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
				}
				
				return entity;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}

	
	@Override
	public boolean save(HolidayRequestEntity holidayRequestEntity) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "INSERT INTO holiday_request (id, employeeid, status, startdate, enddate, created, modified) "
					+ "VALUES (?,?,?,?,?,?,?);";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, holidayRequestEntity.getId());
				stmt.setObject(2, holidayRequestEntity.getEmployeeId());
				stmt.setString(3, holidayRequestEntity.getStatus());
				stmt.setObject(4, holidayRequestEntity.getStartDate());
				stmt.setObject(5, holidayRequestEntity.getEndDate());
				stmt.setObject(6, holidayRequestEntity.getCreated());
				stmt.setObject(7, holidayRequestEntity.getModified());
				
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
	public List<HolidayRequestEntity> getHolidayRequests() {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT id, employeeid, status, startdate, enddate, created, modified "
					+ "FROM holiday_request";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				List<HolidayRequestEntity> holidayRequests = new ArrayList<>();
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					HolidayRequestEntity entity = new HolidayRequestEntity();
					entity.setId(UUID.fromString(rs.getString("id")));
					entity.setEmployeeId(UUID.fromString(rs.getString("employeeid")));
					entity.setStatus(rs.getString("status"));
					entity.setStartDate(LocalDateTime.parse(rs.getString("startdate"), DateUtils.FORMATTER));
					entity.setEndDate(LocalDateTime.parse(rs.getString("enddate"), DateUtils.FORMATTER));
					entity.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					entity.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
					
					holidayRequests.add(entity);
				}
				
				return holidayRequests;	
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}


	@Override
	public HolidayRequestEntity update(UUID id, HolidayRequestEntity holidayRequestEntity) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "UPDATE holiday_request SET status = ?, startdate = ?, enddate = ?, modified = ?, employeeid = ? "
					+ "WHERE id= ?;";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, holidayRequestEntity.getStatus());
				stmt.setObject(2, holidayRequestEntity.getStartDate());
				stmt.setObject(3, holidayRequestEntity.getEndDate());
				stmt.setObject(4, holidayRequestEntity.getModified());
				stmt.setObject(5, holidayRequestEntity.getEmployeeId());
				stmt.setObject(6, id);
				
				if (stmt.executeUpdate() == 1) {
				     return holidayRequestEntity;
				} else {
				     return null;
				}	
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}


	@Override
	public List<HolidayRequestEntity> getHolidayRequestsByStatus(HolidayRequestStatusEnum requestStatus) {
try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT id, employeeid, status, startdate, enddate, created, modified "
					+ "FROM holiday_request "
					+ "WHERE status = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, requestStatus.name());
				
				List<HolidayRequestEntity> holidayRequests = new ArrayList<>();
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					HolidayRequestEntity entity = new HolidayRequestEntity();
					entity.setId(UUID.fromString(rs.getString("id")));
					entity.setEmployeeId(UUID.fromString(rs.getString("employeeid")));
					entity.setStatus(rs.getString("status"));
					entity.setStartDate(LocalDateTime.parse(rs.getString("startdate"), DateUtils.FORMATTER));
					entity.setEndDate(LocalDateTime.parse(rs.getString("enddate"), DateUtils.FORMATTER));
					entity.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					entity.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
					
					holidayRequests.add(entity);
				}
				
				return holidayRequests;	
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
}
