package com.holidaysystem;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HolidayApplication extends Application {
	public HolidayApplication() {
		System.out.println("HolidayApplication");
	}
}
