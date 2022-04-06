package com.holidaysystem.message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.holidaysystem.Constants;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.mail.MailService;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.repository.HolidayRequestRepository;

@ApplicationScoped
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = Constants.JMS_MESSAGE_QUEUE), 
				@ActivationConfigProperty(propertyName = "destinationType", 
				propertyValue = "javax.jms.Queue")
		}, 
		mappedName = Constants.JMS_MESSAGE_QUEUE)
public class HolidayRequestMessageBean implements MessageListener {
	
	@Inject
    private HolidayRequestRepository holidayRequestRepository;
	@Inject
	private HolidayRequestMapper holidayRequestMapper;
	@Inject
	private MailService mailService;
	
    public void onMessage(Message message) {
    	System.out.println("Message received from message queue");
    	
    	try {
    		MapMessage requestMsg = (MapMessage) message;
			String jsonRequest = requestMsg.getString(Constants.QUEUE_KEY_MESSAGE);
			System.out.println(String.format("The jsonRequest: %s ", jsonRequest));
			
			HolidayRequestMessage holidayRequestMessage = holidayRequestMapper.toMessage(jsonRequest);
			HolidayRequestEntity entity = holidayRequestMapper.toEntity(holidayRequestMessage);
			
			holidayRequestRepository.save(entity);
			
			mailService.send();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
    } 
}

