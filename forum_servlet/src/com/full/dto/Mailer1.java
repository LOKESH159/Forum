package com.full.dto;

//import java.net.MalformedURLException;
//import java.net.URL;
import java.util.Properties;
//import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
//import com.google.appengine.api.mail.MailService;
//import com.google.appengine.api.mail.MailServiceFactory;
public class Mailer1 {

	public  Logger logger = Logger.getLogger(Mailer1.class.getName()); 

	 public  void send(String toEMail){
		 String appUrl; 
	        String environment = System.getProperty("com.google.appengine.runtime.environment");
	        if (environment.equals("Production")) {
	        	System.out.println("IAM IN PRODUCTION");
	            String applicationId = System.getProperty("com.google.appengine.application.id");
	            String version = System.getProperty("com.google.appengine.application.version");
	            appUrl = "http://"+version+"."+applicationId+".appspot.com/SingUpConfirmationServlet?eMail="+toEMail;
	        } else {
	        	System.out.println("IAM NOT IN PRODUCTION");
	            appUrl = "http://localhost:8888";
	        }
	 logger.info("THE URL IS ="+appUrl);
	 StringBuffer sb=new StringBuffer();
	 sb.append("<html><body>");
	 sb.append("<h1>FORUM CONFIRMATION MAIL</h1>"+"<br/>"); 	
	 sb.append("<a href="+appUrl+">PLEASE CLICK HERE FOR CONFIRMATION</a>");
	 System.out.println("THE URL IS ="+appUrl);
	 sb.append("</table>"+"</body>"+"</html>");            	
     String link=sb.toString();
     logger.info("THE MESSAGE IS ="+link);
	        Properties props = new Properties();
	        Session session = Session.getDefaultInstance(props, null);
	 
	        try {
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress("lokesh.rao@full.co", "Test Email"));
	            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEMail, "Mr. User"));
	            msg.setSubject("Password Retest Request");
	            msg.setText("Please use the following link for confimation "
	                    +appUrl);
	            logger.info("THE TEXT IS=");
	            logger.info("MESSAGE IS READY");
	            Transport.send(msg);
	            logger.info("MESSAGE SENT SUCCESSFULLY");
	            System.out.println("Email Sent");
	        } catch (Exception e) {
	                logger.info("I GOT EXCEPTION ="+e.getMessage());
	        } 
	 }
	 
}
