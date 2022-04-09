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

/**
 * 
 * @author yauhen bichel
 *
 */
@ApplicationScoped
public class MailService implements IMailService {
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

	            //Transport.send(message, username, password);
			    Transport.send(message);

	            System.out.println("Email Message Sent Successfully");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }	      
	   }
}