package com.holidaysystem.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.model.EmployeeResponse;
import com.holidaysystem.model.HolidayRequest;
import com.holidaysystem.model.HolidayResponse;
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
    	
        return Response.ok(holidayRequestResponses).build();
    }
    
    @GET()
    @Path("/{id}")
    public Response getHolidayRequestById(@PathParam("id") UUID id) {
    	HolidayRequestEntity entity = holidayRequestRepository.findById(id);
    	HolidayResponse holidayRequestResp = holidayRequestMapper.toResponse(entity);
        return Response.ok(holidayRequestResp).build();
    }
    
    @POST()
    @Path("/create")
    public Response createHolidayRequest(HolidayRequest holidayRequest) {    	
    	HolidayRequestEntity entity = holidayRequestMapper.toEntity(holidayRequest);
    	holidayRequestRepository.save(entity);
    	HolidayResponse holidayRequestResp = holidayRequestMapper.toResponse(entity);
    	
        return Response.ok(holidayRequestResp).build();
    }
}
