package com.holidaysystem.common.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import com.holidaysystem.Constants;

/**
 * Handle exception and return user friendly message about error in server side
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Provider
public class UncaughtExceptionMapper implements ExceptionMapper<Throwable> {
    
    private static final Logger logger = Logger.getLogger(UncaughtExceptionMapper.class);
  
    @Override
    public Response toResponse(Throwable ex) {
    	logger.error(ex.getMessage(), ex);
    	
        return Response
        		.status(Response.Status.INTERNAL_SERVER_ERROR)
        		.entity(Constants.UNCAUGHT_EXCEPTION_MESSAGE)
        		.type("text/plain")
        		.build();
    }
}
