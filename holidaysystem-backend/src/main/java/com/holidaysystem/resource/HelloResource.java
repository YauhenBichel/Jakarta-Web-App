package com.holidaysystem.resource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST API for checking the system is running
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Path("/hello")
@RequestScoped
public class HelloResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		
		return Response.status(Response.Status.OK)
				.entity("Vacation system is running")
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
}
