package com.full.dto;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;
import com.google.appengine.api.mail.MailService.Message;


public class Mailer {
	
	public  Logger logger = Logger.getLogger(Mailer.class.getName()); 

	 public  void send(String toEMail){
	        Properties props = new Properties();
		 Session session = Session.getDefaultInstance(props);
	    //    MimeMessage email = new MimeMessage(session);
         MailService mailService=MailServiceFactory.getMailService();
         
	        try {
	        	Message message=new Message();
	        	 URL url = new URL("http://forum-188810.appspot.com/SingUpConfirmationServlet?eMail="+toEMail);
	        	 StringBuffer sb=new StringBuffer();
	        	 sb.append("<html><body>");
	        	 sb.append("<h1>FORUM CONFIRMATION MAIL</h1>"+"<br/>"); 	
	        	 sb.append("<a href="+url+">PLEASE CLICK HERE FOR CONFIRMATION</a>");
	        	 System.out.println("THE URL IS ="+url);
	        	 sb.append("</table>"+"</body>"+"</html>");            	
	             String link=sb.toString();
	        	message.setReplyTo("lokesh.rao@full.co");
	        	message.setHtmlBody(link);
	        	message.setSender("lokesh.rao@full.co");
	        	message.setTo(toEMail);
	        	message.setSubject("FORUM CONFIRMATION");
	        	//message.setHeaders( new MailService.Header("Content-type", "text/HTML; charset=UTF-8"));
	        /*	 email.addHeader("Content-type", "text/HTML; charset=UTF-8");
	             email.setFrom(new InternetAddress("lokesh.rao@full.co"));   	      
	             email.setReplyTo(InternetAddress.parse("ss5@full.io", false));
	             email.setSubject("FORUM CONFIRMATION");
	             email.setSentDate(new Date());*/
        
        /*       Multipart mp = new MimeMultipart();
        	 MimeBodyPart body = new MimeBodyPart();
        	 body.setText(link, "text/html; charset=UTF-8");
        	 mp.addBodyPart(body);*/
          logger.info("THE BODY IN MAIL IS ="+link);
        /*  email.setContent(mp);*/
   	  logger.info("Message is ready");
       	
       	  mailService.send(message);
    
       	  logger.info("message sent successfully");    
         } catch ( MalformedURLException e) {
        	 logger.info("I GOT EXCEPTION IN MAILER");
        	 logger.log(Level.INFO, e.getMessage(), e);
        	 logger.info("exception is "+e.getMessage());
        	 }   
	        catch(Exception e1)
	        {
	        	 logger.info("I GOT EXCEPTION IN MAILER");
	        	 logger.info("exception main is "+e1.getMessage());
	        }
            
   } 
}
