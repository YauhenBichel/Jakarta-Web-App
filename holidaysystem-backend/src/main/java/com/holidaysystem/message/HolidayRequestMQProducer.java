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

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.vo.HolidayRequest;

/**
 * Sends a message to message system
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
public class HolidayRequestMQProducer {
	
	private static final Logger logger = Logger.getLogger(HolidayRequestMQProducer.class);
	
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
			
			logger.debug("Sending message");
			
			sender.send(message);
			connect.close();
			
		} catch (NamingException e) {
			logger.error(e.getMessage(), e);
		} catch (JMSException e) {
			logger.error(e.getMessage(), e);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}

