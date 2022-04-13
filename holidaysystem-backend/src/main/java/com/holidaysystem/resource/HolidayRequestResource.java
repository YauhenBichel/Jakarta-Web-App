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
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.message.HolidayRequestMQProducer;
import com.holidaysystem.vo.HolidayRequest;
import com.holidaysystem.vo.HolidayResponse;
import com.holidaysystem.repository.HolidayRequestRepository;
import com.holidaysystem.repository.IHolidayRequestRepository;
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
    
    @GET
    @Path("/all")
    public Response getHolidayRequests() {
    	
    	logger.debug("getHolidayRequests()");
    	
    	List<HolidayResponse> holidayRequestResponses = holidayRequestService.getHolidayRequests();
    	
        return Response.ok(holidayRequestResponses)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @GET
    @Path("/all/{status}")
    public Response getHolidayRequests(@PathParam("status") HolidayRequestStatusEnum status) {
    	
    	logger.info("getHolidayRequests() with status " + status.name());
    	
    	List<HolidayResponse> holidayRequestResponses = holidayRequestService.getHolidayRequestsByStatus(status);
    	
        return Response.ok(holidayRequestResponses)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @GET()
    @Path("/{id}")
    public Response getHolidayRequestById(@PathParam("id") UUID id) {
    	HolidayRequestEntity entity = holidayRequestRepository.findById(id);
    	HolidayResponse holidayRequestResp = holidayRequestMapper.toResponse(entity);
        return Response.ok(holidayRequestResp)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @POST()
    public Response createHolidayRequest(HolidayRequest holidayRequest) { 
    	UUID id = UUID.randomUUID();
    	HolidayRequestEntity entity = holidayRequestMapper.toEntity(id, holidayRequest);
    	holidayRequestRepository.save(entity);
    	holidayRequestMQProducer.publish(id, holidayRequest);
        
        return Response.ok(id)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @PUT()
    @Path("/{id}")
    public Response updateHolidayRequest(@PathParam("id") UUID id, HolidayRequest holidayRequest) {
    	HolidayRequestEntity dbEntity = holidayRequestRepository.findById(id);
    	
    	if(dbEntity != null) {
    		HolidayRequestEntity entity = holidayRequestMapper.toEntity(holidayRequest, dbEntity);
        	holidayRequestRepository.update(id, entity);
        	HolidayResponse holidayRequestResp = holidayRequestMapper.toResponse(entity);
        	
        	return Response.ok(holidayRequestResp)
        			.header("Access-Control-Allow-Origin", "*")
        			.build();
    	}
    	
    	return Response.ok(null)
    			.header("Access-Control-Allow-Origin", "*")
    			.build();
        
    }
}
