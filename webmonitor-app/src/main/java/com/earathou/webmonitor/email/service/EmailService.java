package com.earathou.webmonitor.email.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.earathou.webmonitor.email.EmailContactInfo;
import com.earathou.webmonitor.email.EmailFactory;
import com.earathou.webmonitor.ping.service.PingWebService;
import com.earathou.webmonitor.website.WebsiteForm;

@Service
public class EmailService implements EmailFactory {
	
	private EmailContactInfo senderEmail;
	private EmailContactInfo recipientEmail;
	private String body;
	private String subject;
	
	private String smtpUsername;
	private String smtpPassword;
	private String host;
	
	private String filePath;
	private static final String DEFAULT_EMAIL_PROPERTIES = "email.properties";
	private ArrayList<WebsiteForm> websites;
	
	// Session object to represent a mail session with the specified properties.
	private Session session;
		
	public EmailService() {
		filePath = DEFAULT_EMAIL_PROPERTIES;
		loadProperties();
	}
	
	public EmailService(String filePath) {
		this.filePath = filePath;
		loadProperties();
	}

	@Override
	public void loadProperties() {
		Properties props = new Properties();
		InputStream input = null;
		senderEmail = new EmailContactInfo();
		recipientEmail = new EmailContactInfo();
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			
			// Open the email properties
			input = classLoader.getResourceAsStream(filePath);

			// load a properties file
			props.load(input);

			senderEmail.setEmailAddress(props.getProperty(FROM));
			senderEmail.setName(props.getProperty(FROM_NAME));
			recipientEmail.setEmailAddress(props.getProperty(TO));
			smtpUsername = props.getProperty(SMTP_USERNAME);
			smtpPassword = props.getProperty(SMTP_PASSWORD);
			subject = props.getProperty(EMAIL_SUBJECT);
			body = props.getProperty(EMAIL_BODY);
			host  = props.getProperty(MAIL_HOST);
			
		} catch (IOException ex) {
			System.err.println("I/O Error: " + ex.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					System.out.println("I/O Error on Closing: " + e.getMessage());
				}
			}
		}

		// Create a Session object to represent a mail session with the
		// specified properties.
		session = Session.getDefaultInstance(props);
	}

	@Override
	public void sendMessage() {
		// Create a message with the specified information.
		MimeMessage msg = new MimeMessage(session);
		Transport transport = null;
		try {
			msg.setFrom(new InternetAddress(senderEmail.getEmailAddress(), senderEmail.getName()));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail.getEmailAddress()));
			msg.setSubject(subject);
			
			StringBuffer bodyWithList = new StringBuffer(body);
			bodyWithList.append("<ul>");
			
			for (WebsiteForm website : websites) {
				if (PingWebService.WEBPAGE_STATUS_NOT_AVAILABLE.equals(website.getStatus())) 
					bodyWithList.append("<li>").append(website).append("</li>");
			}
			bodyWithList.append("</ul>");
			
			msg.setContent(bodyWithList.toString(), "text/html");

			// Create a transport.
			transport = session.getTransport();

			// Send the message.
			System.out.println("Sending...");

			// Connect to using the SMTP username and password you specified above.
			transport.connect(host, smtpUsername, smtpPassword);
			
			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		} finally {
			// Close and terminate the connection.
			try {
				if (transport != null)
					transport.close();
			} catch (MessagingException e) {
				System.out.println("MessagingException message: " + e.getMessage());
			}
		}
	}

	public ArrayList<WebsiteForm> getWebsites() {
		return websites;
	}

	public void setWebsites(ArrayList<WebsiteForm> websites) {
		this.websites = websites;
	}
}
