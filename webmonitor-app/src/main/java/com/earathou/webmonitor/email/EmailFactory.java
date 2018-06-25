package com.earathou.webmonitor.email;

public interface EmailFactory {
	
	public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	public static final String MAIL_SMTP_PORT = "mail.smtp.port";
	public static final String MAIL_HOST = "mail.host";

	public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

	public static final String FROM = "mail.sender.emailAddress";
	public static final String FROM_NAME = "mail.sender.name";
	public static final String TO = "mail.recipient.emailAddress";
	public static final String TO_NAME = "mail.sender.emailAddress";
	
	public static final String SMTP_USERNAME = "mail.smtp.username";
	public static final String SMTP_PASSWORD = "mail.smtp.password";
	
	public static final String EMAIL_SUBJECT = "mail.message.error.subject";
	public static final String EMAIL_BODY = "mail.message.error.body";
	
	public void loadProperties();
	
	public void sendMessage();
}
