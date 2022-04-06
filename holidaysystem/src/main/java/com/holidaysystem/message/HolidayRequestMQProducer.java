package com.holidaysystem.message;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.holidaysystem.Constants;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.vo.HolidayRequest;

@ApplicationScoped
public class HolidayRequestMQProducer {
	
	@Inject
    private HolidayRequestMapper holidayRequestMapper;
	
	public void publish(UUID id, HolidayRequest request) {
		
    	String jsonEntity = holidayRequestMapper.toJson(id, request);
		
		try {
			Context jndiContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory) jndiContext.lookup(Constants.JNDI_CONNECTION_FACTORY);
			Queue calculationQueue = (Queue) jndiContext.lookup(Constants.JMS_MESSAGE_QUEUE);
			Connection connect = factory.createConnection();

			Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer sender = session.createProducer(calculationQueue);
			MapMessage message = session.createMapMessage();
			message.setString(Constants.QUEUE_KEY_MESSAGE, jsonEntity);
			System.out.println("Sending message");
			sender.send(message);
			connect.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

