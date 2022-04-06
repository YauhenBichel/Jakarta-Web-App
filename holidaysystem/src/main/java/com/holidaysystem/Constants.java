package com.holidaysystem;

public final class Constants {
	public static final String JMS_PREFIX = "java:/jms/";
	public static final String MESSAGE_QUEUE = "HolidayRequestQueue";
	public static final String JMS_MESSAGE_QUEUE = JMS_PREFIX + MESSAGE_QUEUE;
	public static final String HOLIDAY_REQUEST_TOPIC = "HolidayRequestTopic";
	public static final String JMS_HOLIDAY_REQUEST_TOPIC = JMS_PREFIX + HOLIDAY_REQUEST_TOPIC;
	public static final String JNDI_CONNECTION_FACTORY = "java:/ConnectionFactory";
	
	public static final String DATASOURCE_LOOKUP_KEY = "java:/PostgresDS";
}
