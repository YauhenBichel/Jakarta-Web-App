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

@ApplicationScoped
public class MailService implements IMailService {
	public void send() {
	      //provide recipient's email ID
	      String to = "jakartato@example.com";

	      //provide sender's email ID
	      String from = "jakartafrom@example.com";
	      final String username = "Holiday System Admin";
	      //provide Mailtrap's password
	      final String password = "82a851fcf4aa33";

	      //provide Mailtrap's host address
	      String host = "smtp.mailtrap.io";
	      //configure Mailtrap's SMTP server details
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      //create the Session object
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	    }
	         });

	      try {
	    //create a MimeMessage object
	    Message message = new MimeMessage(session);

	    //set From email field
	    message.setFrom(new InternetAddress(from));

	    //set To email field
	    message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));

	    //set email subject field
	    message.setSubject("Here comes Jakarta Mail!");

	    //set the content of the email message
	    message.setText("Just discovered that Jakarta Mail is fun and easy to use");

	    //send the email message
	    Transport.send(message);

	    System.out.println("Email Message Sent Successfully");

	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	   }
}
