package com.holidaysystem.resource;

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
 * @author yauhen bichel
 *
 */
@Path("/request-alert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
