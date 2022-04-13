package com.holidaysystem.mail;

import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;

import jakarta.inject.Named;

/**
 * Mail service for sending message to admin
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
@Named("DeafultMailService")
public class MailService implements IMailService {
	
	private static final Logger logger = Logger.getLogger(MailService.class);
	
	public void send() {
	      //provide recipient's email ID
	      String to = "yb3129h@gre.ac.uk";

	      String from = "help.holiday.request@gmail.com";
	      final String username = "Holiday Request System";
	      final String password = "1Vaction2!Request%";


	      Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true");
	        prop.put("mail.smtp.starttls.required", "true");

	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(from, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(from));
	            message.setRecipients(Message.RecipientType.TO,
			               InternetAddress.parse(to));
	            
	            message.setSubject("COMP1610 Group 5 Notification for Admin!");
			    message.setText("There is a new request!");

			    Transport.send(message);

	            logger.debug("Email Message Sent Successfully");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	            logger.error(e.getMessage(), e);
	        }	      
	   }
}
