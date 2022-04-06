package com.holidaysystem.message;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.UUID;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.holidaysystem.Constants;


@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = Constants.JMS_MESSAGE_QUEUE), 
				@ActivationConfigProperty(propertyName = "destinationType", 
				propertyValue = "javax.jms.Queue")
		}, 
		mappedName = Constants.JMS_MESSAGE_QUEUE)
public class HolidayRequestMessageBean implements MessageListener {
	
    public HolidayRequestMessageBean() {}
	
    public void onMessage(Message message) {
    	System.out.println("Message received by MDB");
    	
    	try {
    		MapMessage requestMsg = (MapMessage) message;
			String title = requestMsg.getString("title");
			UUID id = insertHolidayRequest(title);
			
			System.out.println(String.format("The title: %s with ID %s ", title, id));
			
			deliverResult(requestMsg, id);
						
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void deliverResult(MapMessage msg, UUID id) throws JMSException, NamingException {
		Context jndiContext = new InitialContext();
		ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup(Constants.JNDI_CONNECTION_FACTORY);
		
		Topic resultTopic = (Topic) jndiContext.lookup(Constants.JMS_HOLIDAY_REQUEST_TOPIC);
		Connection connect = connectionFactory.createConnection();
		Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);

		MessageProducer sender = session.createProducer(resultTopic);
		TextMessage message = session.createTextMessage();

		message.setText(String.format("New message with ID %d ", id));
		sender.send(message);
		connect.close();
	}
    
    public UUID insertHolidayRequest(String msg) {
    	HolidayRequestMessage message = new HolidayRequestMessage();
    	message.setId(UUID.randomUUID());

		

		return message.getId();
	}

}

