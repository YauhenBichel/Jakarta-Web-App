package com.holidaysystem.repository;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.common.DateUtils;
import com.holidaysystem.entity.HolidayDetailsEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * HolidayDetails Repository implementation using java.sql.PreparedStatement
 * @author yauhen bichel
 *
 */
@ApplicationScoped
public class HolidayDetailsRepository implements IHolidayDetailsRepository {

	private static final Logger logger = Logger.getLogger(HolidayDetailsRepository.class);
	
	@Override
	public HolidayDetailsEntity findById(UUID id) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "SELECT id, employeeid, totaldays, takendays, created, modified "
					+ "FROM holiday_details where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, id);
			
			HolidayDetailsEntity entity = new HolidayDetailsEntity();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				entity.setId(UUID.fromString(rs.getString("id")));
				entity.setEmployeeId(UUID.fromString(rs.getString("firstname")));
				entity.setTotalDays(rs.getInt("totaldays"));
				entity.setTakenDays(rs.getInt("takendays"));
				entity.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
				entity.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
			}
			
			return entity;
			
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}


	@Override
	public boolean save(HolidayDetailsEntity entity) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String query = "INSERT INTO holiday_details (id, employeeid, totaldays, takendays, "
					+ " created, modified) "
					+ "VALUES (?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setObject(1, entity.getId());
			ps.setObject(2, entity.getEmployeeId());
			ps.setObject(3, entity.getTotalDays());
			ps.setObject(4, entity.getTakenDays());
			ps.setObject(9, entity.getCreated());
			ps.setObject(10, entity.getModified());
			
			if (ps.executeUpdate() == 1) {
			     return true;
			} else {
			     return false;
			}
			
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return false;
	}

	@Override
	public List<HolidayDetailsEntity> getHolidayDetails() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "SELECT id, employeeid, totaldays, takendays, created, modified "
					+ " FROM holiday_details;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
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
			
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
}
