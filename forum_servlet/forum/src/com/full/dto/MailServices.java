package com.full.dto;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 

public class MailServices {
	public  Logger logger = Logger.getLogger(MailServices.class.getName()); 
	public  void sendSimpleMail(String usereMail,String customerId) {
        String appUrl; 
        String environment = System.getProperty("com.google.appengine.runtime.environment");
        if (environment.equals("Production")) {
            String applicationId = System.getProperty("com.google.appengine.application.id");
            String version = System.getProperty("com.google.appengine.application.version");
            appUrl = "http://"+version+"."+applicationId+".appspot.com/SingUpConfirmationServlet?eMail="+usereMail;
        } else {
            appUrl = "http://localhost:8888";
        }
        logger.info("THE URL IS ="+appUrl);
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
 
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("lokesh.rao@full.co", "Test Email"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usereMail, "Mr. User"));
            msg.setSubject("FORUM CONFIRMATION");
            msg.setText("THE CUSTOMER ID IS ="
                    +customerId); 
            logger.info("THE TEXT IS="+customerId);
            logger.info("MESSAGE IS READY");
            Transport.send(msg);
            logger.info("MESSAGE SENT SUCCESSFULLY");
            System.out.println("Email Sent");
        } catch (Exception e) {
                
        } 
 
    }
 
 
}
