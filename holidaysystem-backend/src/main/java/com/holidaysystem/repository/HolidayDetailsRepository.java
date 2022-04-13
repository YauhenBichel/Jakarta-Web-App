package com.holidaysystem.repository;

import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.common.DateUtils;
import com.holidaysystem.entity.HolidayDetailsEntity;

import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * HolidayDetails Repository implementation using java.sql.PreparedStatement
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
@Default
public class HolidayDetailsRepository implements IHolidayDetailsRepository {

	private static final Logger logger = Logger.getLogger(HolidayDetailsRepository.class);
	
	@Resource(lookup = Constants.DATASOURCE_LOOKUP_KEY)
    DataSource dataSource;
	
	@Override
	public HolidayDetailsEntity findById(UUID id) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT id, employeeid, totaldays, takendays, created, modified "
					+ "FROM holiday_details "
					+ "WHERE id = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, id);
				
				HolidayDetailsEntity entity = new HolidayDetailsEntity();
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					entity.setId(UUID.fromString(rs.getString("id")));
					entity.setEmployeeId(UUID.fromString(rs.getString("employeeid")));
					entity.setTotalDays(rs.getInt("totaldays"));
					entity.setTakenDays(rs.getInt("takendays"));
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
	public HolidayDetailsEntity findByEmployeeId(UUID employeeId) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT id, employeeid, totaldays, takendays, created, modified "
					+ "FROM holiday_details "
					+ "WHERE employeeId = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, employeeId);
				
				HolidayDetailsEntity entity = new HolidayDetailsEntity();
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					entity.setId(UUID.fromString(rs.getString("id")));
					entity.setEmployeeId(UUID.fromString(rs.getString("employeeid")));
					entity.setTotalDays(rs.getInt("totaldays"));
					entity.setTakenDays(rs.getInt("takendays"));
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
	public boolean save(HolidayDetailsEntity entity) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "INSERT INTO holiday_details (id, employeeid, totaldays, takendays, "
					+ " created, modified) "
					+ "VALUES (?,?,?,?,?,?);";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, entity.getId());
				stmt.setObject(2, entity.getEmployeeId());
				stmt.setObject(3, entity.getTotalDays());
				stmt.setObject(4, entity.getTakenDays());
				stmt.setObject(5, entity.getCreated());
				stmt.setObject(6, entity.getModified());
				
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
	public List<HolidayDetailsEntity> getHolidayDetails() {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT id, employeeid, totaldays, takendays, created, modified "
					+ " FROM holiday_details;";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				List<HolidayDetailsEntity> entities = new ArrayList<>();
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					HolidayDetailsEntity entity = new HolidayDetailsEntity();
					
					entity.setId(UUID.fromString(rs.getString("id")));
					entity.setEmployeeId(UUID.fromString(rs.getString("firstname")));
					entity.setTotalDays(rs.getInt("totaldays"));
					entity.setTakenDays(rs.getInt("takendays"));
					entity.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
					entity.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
					
					entities.add(entity);
				}
				
				return entities;
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
}
