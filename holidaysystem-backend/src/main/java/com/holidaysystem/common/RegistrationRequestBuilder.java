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

import com.holidaysystem.vo.RegistrationRequest;

/**
 * Builder for RegistrationRequest
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public final class RegistrationRequestBuilder {

	private static RegistrationRequestBuilder builder;
    private RegistrationRequest request;

    private RegistrationRequestBuilder() {
    	request = new RegistrationRequest();
    }
    
    public static RegistrationRequestBuilder builder() {
    	builder = new RegistrationRequestBuilder();
    	return builder;
    }
    
    public RegistrationRequestBuilder setEmail(String email) {
    	if(request == null) {
    		request = new RegistrationRequest();
    	}
        this.request.setEmail(email);
        return this;
    }

    public RegistrationRequestBuilder setPassword(String password) {
    	if(request == null) {
    		request = new RegistrationRequest();
    	}
        this.request.setPassword(password);
        return this;
    }

    public RegistrationRequestBuilder setAuthRole(String authRole) {
    	if(request == null) {
    		request = new RegistrationRequest();
    	}
        this.request.setAuthRole(authRole);
        return this;
    }

    public RegistrationRequest build() {
        return this.request;
    }
}
