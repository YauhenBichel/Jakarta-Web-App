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
package com.holidaysystem;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.jboss.logging.Logger;


/**
 * Entry Point of the application 
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationPath("/api")
public class HolidayApplication extends Application {
	
	private static final Logger logger = Logger.getLogger(HolidayApplication.class);
	
	private Set<Object> singletons = new HashSet<>();
    private Set<Class<?>> classes = new HashSet<>();
	
	public HolidayApplication() {
		logger.debug("HolidayApplication");
	}

    public Set<Class<?>> getClasses() {
        return this.classes;
    }

    public Set<Object> getSingletons() {
        return this.singletons;
    }
}
