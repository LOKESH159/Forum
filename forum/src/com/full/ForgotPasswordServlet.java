package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.helper.ForgotPasswordMail;
@Controller
@RequestMapping("/ForgotpasswordServlet")
public class ForgotPasswordServlet {
private ForgotPasswordMail forgotPasswordMail;
@Autowired
	public void setForgotPasswordMail(ForgotPasswordMail forgotPasswordMail) {
	this.forgotPasswordMail = forgotPasswordMail;
}
private static final long serialVersionUID = 1L;
@RequestMapping("/forgotPassword")
@ResponseBody
	public String forgotPassword(HttpServletRequest req){
		String eMail=req.getParameter("EMAIL");
		System.out.println("EMAIL IS ="+eMail);
		ForgotPasswordMail forgotPasswordMail=new ForgotPasswordMail();
		boolean isSended=forgotPasswordMail.sendMail(eMail);
if(isSended){
	return "true";
}
else{
	return "false";
}
	
	}

}
