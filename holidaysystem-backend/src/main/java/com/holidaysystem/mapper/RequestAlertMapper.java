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
package com.holidaysystem.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.holidaysystem.entity.RequestAlertEntity;
import com.holidaysystem.message.HolidayRequestMessage;
import com.holidaysystem.vo.RequestAlertResponse;

/**
 * Request Alert mapper
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
public class RequestAlertMapper {
	
	public RequestAlertEntity toEntity(UUID alertId, HolidayRequestMessage holidayRequestMessage) {
		RequestAlertEntity entity = new RequestAlertEntity();
    	entity.setId(alertId);
    	entity.setRequestId(holidayRequestMessage.getId());
    	entity.setDate(LocalDateTime.now());
    	entity.setCreated(LocalDateTime.now());
    	entity.setModified(LocalDateTime.now());
    	
    	return entity;
	}

	public RequestAlertResponse toResponse(RequestAlertEntity entity) {
		RequestAlertResponse requestAlertResponse = new RequestAlertResponse();
		requestAlertResponse.setId(entity.getId());
		requestAlertResponse.setRequestId(entity.getRequestId());
		requestAlertResponse.setDate(entity.getDate().toString());
		
		return requestAlertResponse;
	}
}
