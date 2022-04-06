package com.holidaysystem.message;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.holidaysystem.Constants;

public class HolidayReaderConsumer implements MessageListener {

	public HolidayReaderConsumer()
	{
		Context jndiContext;
		
		try {
			jndiContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory)jndiContext.lookup(Constants.JNDI_CONNECTION_FACTORY);
	      
		    Topic resultTopic = (Topic) jndiContext.lookup(Constants.JMS_HOLIDAY_REQUEST_TOPIC);
		    
		    Connection connect = factory.createConnection();      
			Session session = connect.createSession(false,Session.AUTO_ACKNOWLEDGE);      
			MessageConsumer receiver = session.createConsumer(resultTopic); 
			
			receiver.setMessageListener(this);
			
			System.out.println ("ReaderConsumer Listening for messages on java:/jms/HolidayRequestTopic...");
			connect.start ();
			    
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(Message message) {

		try {
			TextMessage objMsg = (TextMessage)message;
			String msg = objMsg.getText();
			
			System.out.println ("Reader going to find and read: " + msg);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
