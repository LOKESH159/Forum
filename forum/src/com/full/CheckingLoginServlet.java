package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@SuppressWarnings("serial")
@Controller
@RequestMapping("/CheckingLogin")
public class CheckingLoginServlet {

@RequestMapping(value="/checkLogin",method=RequestMethod.GET)
@ResponseBody
	public String checkLogin(HttpServletRequest req){
	
		System.out.println("IAM IN CheckingLoginServlet SERVLET");
		HttpSession session=req.getSession(false);
		if(session!=null){
			return "valid";
		}
		else{
		return "Invalid";
		}
		
	
	}



}
