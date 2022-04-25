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
        	.add("Access-Control-Allow-Origin", "*");
    	responseContext.getHeaders()
          .add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders()
          .add("Access-Control-Allow-Headers", "*");
        responseContext.getHeaders()
          .add("Access-Control-Allow-Methods", "*");
    }
}