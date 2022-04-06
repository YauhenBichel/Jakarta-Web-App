package com.holidaysystem.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.vo.HolidayRequest;
import com.holidaysystem.vo.HolidayResponse;
import com.holidaysystem.repository.HolidayRequestRepository;

@Path("holiday-request")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HolidayRequestResource {

    @Inject
    private HolidayRequestRepository holidayRequestRepository;
    @Inject
    private HolidayRequestMapper holidayRequestMapper;
    
    @GET
    @Path("/all")
    public Response getHolidayRequests() {
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
    	HolidayResponse holidayRequestResp = holidayRequestMapper.toResponse(entity);
    	
        return Response.ok(holidayRequestResp)
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
