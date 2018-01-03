package com.full;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.helper.CheckEMail;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/CheckEMailServlet")
public class CheckEMailServlet {
	private CheckEMail checkEMail;
	@Autowired
	public void setCheckEMail(CheckEMail checkEMail) {
		this.checkEMail = checkEMail;
	}
	@RequestMapping(value="/checkUser",method=RequestMethod.GET)
	@ResponseBody
	public String checkingUser(@RequestParam("EMAIL") String eMail) {
		System.out.println("IAM IN CHECK EMAIL SERVLET");
		System.out.println(eMail);
		/*CheckEMail checkEMail=new CheckEMail();*/
	boolean isExist=checkEMail.isEMailExist(eMail);
	
	System.out.println(isExist);
	
	if(isExist){
		return "true";
	}

		return "false";

	
	}
	
/*	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req, resp);
	}*/

}
