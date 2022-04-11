package com.holidaysystem.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.jsonwebtoken.io.IOException;

/**
 * 
 * @author yauhen bichel
 *
 */
@Path("/hello")
public class HelloResource {

	@Context
    private UriInfo context;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		
		throw new IOException("test");
		/*
		return Response.status(Response.Status.OK)
				.entity("Vacation system is running")
				.header("Access-Control-Allow-Origin", "*")
				.build();
				*/
	}
}
