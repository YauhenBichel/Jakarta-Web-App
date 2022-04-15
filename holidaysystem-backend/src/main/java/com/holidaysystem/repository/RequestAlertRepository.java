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
import com.holidaysystem.entity.RequestAlertEntity;

import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * RequestAlert Repository implementation using java.sql.PreparedStatement
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
@Default
public class RequestAlertRepository implements IRequestAlertRepository {

	private static final Logger logger = Logger.getLogger(RequestAlertRepository.class);
	
	@Resource(lookup = Constants.DATASOURCE_LOOKUP_KEY)
    private DataSource dataSource;
	
	@Override
	public RequestAlertEntity findById(UUID requestAlertId) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT id, requestid, date, created, modified FROM request_alert_queue WHERE id = ?";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}

	
	@Override
	public boolean save(RequestAlertEntity requestAlertEntity) {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "INSERT INTO request_alert_queue (id, requestid, date, created, modified) "
					+ "VALUES (?,?,?,?,?);";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setObject(1, requestAlertEntity.getId());
				stmt.setObject(2, requestAlertEntity.getRequestId());
				stmt.setObject(3, requestAlertEntity.getDate());
				stmt.setObject(4, requestAlertEntity.getCreated());
				stmt.setObject(5, requestAlertEntity.getModified());
				
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
	public List<RequestAlertEntity> getRequestAlerts() {
		try (Connection connection = dataSource.getConnection()) {
			
			final String query = "SELECT id, requestid, date, created, modified FROM request_alert_queue";
			
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		return null;
	}
}
