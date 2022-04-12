package com.holidaysystem.common;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders()
          .add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders()
          .add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        responseContext.getHeaders()
          .add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
    }
}