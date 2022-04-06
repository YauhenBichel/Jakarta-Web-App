package com.holidaysystem.message;

import java.io.PrintWriter;

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

public class HolidayRequestProducer {
	public void publish() {
		try {
			Context jndiContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory) jndiContext.lookup(Constants.JNDI_CONNECTION_FACTORY);
			Queue calculationQueue = (Queue) jndiContext.lookup(Constants.JMS_MESSAGE_QUEUE);
			Connection connect = factory.createConnection();

			Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer sender = session.createProducer(calculationQueue);
			String name = "";
			MapMessage message = session.createMapMessage();
			message.setString("title", name);
			System.out.println("Sending message");
			sender.send(message);
			connect.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

