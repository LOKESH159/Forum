package com.full.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.full.dto.Mailer;
import com.full.model.ForgotPasswordMailDao;
@Service
public class ForgotPasswordMail {
	private ForgotPasswordMailDao forgotPasswordMailDao;
	@Autowired
	public void setForgotPasswordMailDao(ForgotPasswordMailDao forgotPasswordMailDao) {
		this.forgotPasswordMailDao = forgotPasswordMailDao;
	}
	public static Logger logger = Logger.getLogger(Mailer.class.getName());
	public  boolean sendMail(String eMail){
		 Properties properties = new Properties();
		 Session session = Session.getDefaultInstance(properties, null);
		 System.out.println("Properties value is ="+properties);
	
         try { 
        	 String customerId=forgotPasswordMailDao.retrieveUUID(eMail);
        	 URL url = new URL("http://forumfirstapplication.appspot.com/passwordrecovery.jsp?eMail="+eMail);
        StringBuffer sb=new StringBuffer();
    		 sb.append("<html><body>");
    		 sb.append("<h1>PASSWORD RECOVERY MAIL</h1>"+"<br/>");
    	
    		// sb.append("<a href="+url+">PLEASE CLICK HERE FOR PASSWORD RECOVERY</a>");
    		 sb.append("THE CUSTOMER ID IS="+customerId);
    		sb.append("</table>"+"</body>"+"</html>"); 	
          String link=sb.toString();
         // System.out.println("THE BODY IN MAIL IS ="+link);
          logger.info("THE BODY IN MAIL IS ="+link);
          MimeMessage msg = new MimeMessage(session);
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
   	      msg.setFrom(new InternetAddress("lokesh.rao@full.co"));   	      
   	      msg.setReplyTo(InternetAddress.parse("lokesh.rao@full.co", false));
          msg.setSubject("PASSWORD RECOVERY");
   	      msg.setContent(link,"text/html");
   	      msg.setSentDate(new Date());
   	      msg.addRecipient(RecipientType.TO, new InternetAddress(eMail));
   	      logger.info("Message is ready");
       	  Transport.send(msg);  
       	  logger.info("message sent successfully");    
         } catch (MessagingException | MalformedURLException e) {
        	 logger.info("I GOT EXCEPTION IN MAILER");
        	 logger.log(Level.INFO, e.getMessage(), e);
        	 logger.info("exception is "+e.getMessage());
        	 return false;
        	 }    
         return true;
	}

}
