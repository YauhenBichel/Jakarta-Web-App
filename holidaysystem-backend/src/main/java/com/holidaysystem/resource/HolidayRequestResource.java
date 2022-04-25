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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.common.DateUtils;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.model.HolidayRequestModel;
import com.holidaysystem.model.PrioritizedRequestModel;
import com.holidaysystem.vo.HolidayRequest;
import com.holidaysystem.vo.HolidayResponse;
import com.holidaysystem.vo.PrioritizedRecordsRequest;
import com.holidaysystem.service.IHolidayRequestService;

/**
 * REST API for holiday request resource
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Path("/holiday-request")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
//@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"admin_role"}))
public class HolidayRequestResource {

	private static final Logger logger = Logger.getLogger(HolidayRequestResource.class);
	
	@Inject
	private IHolidayRequestService holidayRequestService;
	@Inject
    private HolidayRequestMapper holidayRequestMapper;
    
	/**
	 * Gets all holiday requests
	 * limit 100
	 * @return
	 */
    @GET
    @Path("/all")
    public Response getHolidayRequests() {
    	
    	List<HolidayRequestModel> models = holidayRequestService.getHolidayRequests();
    	
    	List<HolidayResponse> holidayRequestResponses = new ArrayList<>();
    	for(HolidayRequestModel model: models) {
    		HolidayResponse holidayResponse = holidayRequestMapper.toResponse(model);
    		holidayRequestResponses.add(holidayResponse);
    	}
    	
        return Response.ok(holidayRequestResponses)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    /**
     * Gets pages of holiday requests sorted by created by default
     * @param offset skip number of requests
     * @param limit size of the page
     * @return list of HolidayResponse
     */
    @GET
    @Path("/query")
    public Response getHolidayRequestPages(@QueryParam("offset") int offset,
    		@QueryParam("limit") int limit) {
    	
    	List<HolidayRequestModel> models = holidayRequestService.getHolidayRequests(offset, limit);
    	
    	List<HolidayResponse> holidayRequestResponses = new ArrayList<>();
    	for(HolidayRequestModel model: models) {
    		HolidayResponse holidayResponse = holidayRequestMapper.toResponse(model);
    		holidayRequestResponses.add(holidayResponse);
    	}
    	
        return Response.ok(holidayRequestResponses)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @GET
    @Path("/all/{status}")
    public Response getHolidayRequests(@PathParam("status") HolidayRequestStatusEnum status) {
    	
    	logger.info("getHolidayRequests() with status " + status.name());
    	
    	List<HolidayRequestModel> models = holidayRequestService.getHolidayRequestsByStatus(status);
    	
    	List<HolidayResponse> holidayRequestResponses = new ArrayList<>();
    	
    	for(HolidayRequestModel model: models) {
    		HolidayResponse holidayResponse = holidayRequestMapper.toResponse(model);
    		holidayRequestResponses.add(holidayResponse);
    	}
    	
        return Response.ok(holidayRequestResponses)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @GET
    @Path("/all/prioritized")
    public Response getPrioritizedHolidayRequests(PrioritizedRecordsRequest request) {
    	
    	LocalDateTime date = LocalDateTime.parse(request.getDate(), DateUtils.FORMATTER);
    	HolidayRequestStatusEnum requestStatus = HolidayRequestStatusEnum.valueOf(request.getRequestStatus());
    	
    	List<PrioritizedRequestModel> models = holidayRequestService
    			.getPrioritizedHolidayRequests(date, requestStatus);
    	
    	List<UUID> requestIds = new ArrayList<>();
    	
    	for(PrioritizedRequestModel model: models) {
    		requestIds.add(model.getRequestId());
    	}
    	
        return Response.ok(requestIds)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @GET
    @Path("/{id}/alternative-dates")
    public Response getAlternativeDates(@PathParam("id") UUID id) {
    	
    	//holidayRequestService.getAlternativeDates(id);
    	
        return Response.ok()
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @GET()
    @Path("/{id}")
    public Response getHolidayRequestById(@PathParam("id") UUID id) {
    	
    	HolidayRequestModel model = holidayRequestService.fetchModelById(id);
    	
    	HolidayResponse holidayRequestResp = holidayRequestMapper.toResponse(model);
    	
        return Response.ok(holidayRequestResp)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @POST()
    public Response createHolidayRequest(HolidayRequest holidayRequest) { 
    	UUID id = UUID.randomUUID();
    	
    	holidayRequestService.addHolidayRequest(id, holidayRequest);
        return Response.ok(id)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @PUT()
    @Path("/{id}")
    public Response updateHolidayRequest(@PathParam("id") UUID id, HolidayRequest holidayRequest) {
    	HolidayRequestEntity updatedEntity = holidayRequestService.update(id, holidayRequest);
    	
    	if(updatedEntity != null) {
        	HolidayResponse holidayRequestResp = holidayRequestMapper.toResponse(updatedEntity);
        	
        	return Response.ok(holidayRequestResp)
        			.header("Access-Control-Allow-Origin", "*")
        			.build();
    	}
    	
    	return Response.ok(null)
    			.header("Access-Control-Allow-Origin", "*")
    			.build();
        
    }
}
