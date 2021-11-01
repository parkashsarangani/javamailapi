package com.kash.gmaildemo;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		System.out.println("Hello World!");

		String message = "Testing message";
		String subject = "Testing Mail API";
		String to = "om.swe56@gmail.com";
		String from = "programmerparkash@gmail.com";

//		sendEmail(message, subject, to, from);
		sendEmailWithAttachment(subject, to, from);
	}
	
	// email with attachment
	private static void sendEmailWithAttachment(String subject, String to, String from) {

		// setting properties
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step.1: getting the session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("programmerparkash@gmail.com", "ProgrammerKash92");
			}
		});
		session.setDebug(true);
		// Step 2. composing the message, may include[text, multimedia]
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			
			String filePath = "C:\\Users\\Parkash\\Desktop\\profile.jpeg";
			mimeMessage.setFrom(from);
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setSubject(subject);
			
			// creating mimeMiltipart for multipart data
			MimeMultipart mimeMultipart = new MimeMultipart();
			
			// for text part
			MimeBodyPart textMimeBodyPart = new MimeBodyPart();
			textMimeBodyPart.setText("Testing Email with Attachment");
			
			// for attachment/file part
			MimeBodyPart fileMimeBodyPart = new MimeBodyPart();
			fileMimeBodyPart.attachFile(new File(filePath));
			
			// adding both to multipart
			mimeMultipart.addBodyPart(textMimeBodyPart);
			mimeMultipart.addBodyPart(fileMimeBodyPart);
			
			mimeMessage.setContent(mimeMultipart);
			
			// Step.3 Sending the email using transport class
			Transport.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// email with text messages only
	private static void sendEmail(String message, String subject, String to, String from) {

		// setting properties
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step.1: getting the session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("programmerparkash@gmail.com", "ProgrammerKash92");
			}
		});
		session.setDebug(true);
		// Step 2. composing the message, may include[text, multimedia]
		MimeMessage mimeMessage = new MimeMessage(session);
		try {

			mimeMessage.setFrom(from);
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);

			// Step.3 Sending the email using transport class
			Transport.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
