package com.holidaysystem;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 
 * @author yauhen bichel
 *
 */
@ApplicationPath("/api")
public class HolidayApplication extends Application {
	public HolidayApplication() {
		System.out.println("HolidayApplication");
	}
}
