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
package com.holidaysystem.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.message.HolidayRequestMQProducer;
import com.holidaysystem.vo.HolidayRequest;
import com.holidaysystem.vo.HolidayResponse;
import com.holidaysystem.repository.HolidayRequestRepository;
import com.holidaysystem.repository.IHolidayRequestRepository;

/**
 * REST API for holiday request resource
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Path("/request-processing")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
//@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"admin_role"}))
public class RequestProcessingResource {

	private static final Logger logger = Logger.getLogger(RequestProcessingResource.class);
	
    @Inject
    private IHolidayRequestRepository holidayRequestRepository;
    @Inject
    private HolidayRequestMapper holidayRequestMapper;
    
    @GET()
    @Path("/{id}/validate")
    public Response validate(@PathParam("id") UUID holidayRequestId) {
    	
    	logger.debug("validate()");
    	
    	List<HolidayRequestEntity> entities = holidayRequestRepository.getHolidayRequests();
    	List<HolidayResponse> holidayRequestResponses = new ArrayList<>();
    	for(HolidayRequestEntity entity: entities) {
    		HolidayResponse holidayResponse = holidayRequestMapper.toResponse(entity);
    		holidayRequestResponses.add(holidayResponse);
    	}
    	
        return Response.ok(holidayRequestResponses)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
}
