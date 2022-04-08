package com.holidaysystem.repository;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.holidaysystem.Constants;
import com.holidaysystem.DateUtils;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.entity.RequestAlertEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yauhen bichel
 *
 */
@ApplicationScoped
public class RequestAlertRepository implements IRequestAlertRepository {

	@Override
	public RequestAlertEntity findById(UUID requestAlertId) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "SELECT id, requestid, date, created, modified FROM request_alert_queue WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, requestAlertId);
			
			RequestAlertEntity entity = new RequestAlertEntity();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				entity.setId(UUID.fromString(rs.getString("id")));
				entity.setRequestId(UUID.fromString(rs.getString("requestid")));
				entity.setDate(LocalDateTime.parse(rs.getString("startdate"), DateUtils.FORMATTER));
				entity.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
				entity.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
			}
			
			return entity;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	
	@Override
	public boolean save(RequestAlertEntity requestAlertEntity) {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String query = "INSERT INTO request_alert_queue (id, requestid, date, created, modified) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setObject(1, requestAlertEntity.getId());
			ps.setObject(2, requestAlertEntity.getRequestId());
			ps.setObject(3, requestAlertEntity.getDate());
			ps.setObject(4, requestAlertEntity.getCreated());
			ps.setObject(5, requestAlertEntity.getModified());
			
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
	public List<RequestAlertEntity> getRequestAlerts() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(Constants.DATASOURCE_LOOKUP_KEY);
			Connection conn = ds.getConnection();
			
			String sql = "SELECT id, requestid, date, created, modified FROM request_alert_queue";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			List<RequestAlertEntity> requestAlerts = new ArrayList<>();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				RequestAlertEntity entity = new RequestAlertEntity();
				entity.setId(UUID.fromString(rs.getString("id")));
				entity.setRequestId(UUID.fromString(rs.getString("requestid")));
				entity.setDate(LocalDateTime.parse(rs.getString("date"), DateUtils.FORMATTER));
				entity.setCreated(LocalDateTime.parse(rs.getString("created"), DateUtils.FORMATTER));
				entity.setModified(LocalDateTime.parse(rs.getString("modified"), DateUtils.FORMATTER));
				
				requestAlerts.add(entity);
			}
			
			return requestAlerts;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
