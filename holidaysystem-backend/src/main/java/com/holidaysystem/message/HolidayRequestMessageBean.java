package com.holidaysystem.message;

import java.util.UUID;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.entity.RequestAlertEntity;
import com.holidaysystem.mail.MailService;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.mapper.RequestAlertMapper;
import com.holidaysystem.repository.HolidayRequestRepository;
import com.holidaysystem.repository.RequestAlertRepository;

/**
 * 
 * @author yauhen bichel
 *
 */
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
	
	private static final Logger logger = Logger.getLogger(HolidayRequestMessageBean.class);
	
	@Inject
    private RequestAlertRepository requestAlertRepository;
	@Inject
	private HolidayRequestMapper holidayRequestMapper;
	@Inject
	private RequestAlertMapper requestAlertMapper;
	@Inject
	private MailService mailService;
	
    public void onMessage(Message message) {
    	logger.info("Message received from message queue");
    	
    	try {
    		MapMessage requestMsg = (MapMessage) message;
			String jsonRequest = requestMsg.getString(Constants.QUEUE_KEY_MESSAGE);
			logger.info(String.format("The jsonRequest: %s ", jsonRequest));
			
			HolidayRequestMessage holidayRequestMessage = holidayRequestMapper.toMessage(jsonRequest);
			RequestAlertEntity entity = requestAlertMapper.toEntity(UUID.randomUUID(), holidayRequestMessage);
			
			requestAlertRepository.save(entity);
			
			mailService.send();
			
		} catch (JMSException e) {
			logger.error(e.getMessage(), e);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    }
}

