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
package com.holidaysystem.common.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

/**
 * Handle exception and return user friendly message about error in server side
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Provider
public class UncaughtExceptionMapper implements ExceptionMapper<Exception> {
    
    private static final Logger logger = Logger.getLogger(UncaughtExceptionMapper.class);
  
    @Override
    public Response toResponse(Exception ex) {
    	logger.error(ex.getMessage(), ex);
    	
        return Response
        		.status(Response.Status.INTERNAL_SERVER_ERROR)
        		.entity(ex.getMessage())
        		.type("text/plain")
        		.build();
    }
}
