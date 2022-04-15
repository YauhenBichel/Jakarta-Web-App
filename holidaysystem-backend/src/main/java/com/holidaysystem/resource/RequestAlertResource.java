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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.entity.RequestAlertEntity;
import com.holidaysystem.mapper.RequestAlertMapper;
import com.holidaysystem.vo.RequestAlertResponse;
import com.holidaysystem.repository.RequestAlertRepository;

/**
 * REST API for request-alert resource
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Path("/request-alert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
//@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"admin_role"}))
public class RequestAlertResource {
	
	private static final Logger logger = Logger.getLogger(RequestAlertResource.class);
	
    @Inject
    private RequestAlertRepository requestAlertRepository;
    @Inject
    private RequestAlertMapper requestAlertMapper;
    
    @GET
    @Path("/all")
    public Response getRequestsAlerts() {
    	logger.debug("getRequestsAlerts()");
    	
    	List<RequestAlertEntity> entities = requestAlertRepository.getRequestAlerts();
    	List<RequestAlertResponse> requestAlertResponses = new ArrayList<>();
    	for(RequestAlertEntity entity: entities) {
    		RequestAlertResponse holidayResponse = requestAlertMapper.toResponse(entity);
    		requestAlertResponses.add(holidayResponse);
    	}
    	
        return Response.ok(requestAlertResponses)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @GET()
    @Path("/{id}")
    public Response getHolidayRequestById(@PathParam("id") UUID id) {
    	RequestAlertEntity entity = requestAlertRepository.findById(id);
    	RequestAlertResponse requestAlertResp = requestAlertMapper.toResponse(entity);
        return Response.ok(requestAlertResp)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }   
}
