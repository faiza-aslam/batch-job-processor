package com.example.batch.service;

import java.text.MessageFormat;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class MailService {
	
	@Resource(name = "java:jboss/mail/gateway")
    private Session session;
	
	private final Logger logger = LoggerFactory.getLogger(MailService.class);

	public static final String EMAIL_TEMPLATE = "<b>Dear {0} </b>, <br/>This is to remind you that you have an overdue task: <b> \"{1}\" </b> that needs to be submitted urgently. <br/><br/> Regards,<br/>System Admin";
	
    public MailService() {
    }
    
    @PermitAll
    public void sendEmail(String emailAddress, String userName, String taskName) {

        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
            message.setSubject("Overdue Task - Reminder");
            message.setFrom(new InternetAddress("admin@domain.com"));

            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();

            String msg = MessageFormat.format(EMAIL_TEMPLATE, userName, taskName);
            
            //HTML mail content
            messageBodyPart.setContent(msg, "text/html");

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
    }

}
