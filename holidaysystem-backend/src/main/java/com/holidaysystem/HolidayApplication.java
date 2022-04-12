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
