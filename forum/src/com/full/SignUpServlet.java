package com.full;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.dto.GenerateUUID;
import com.full.dto.SignUpDto;
import com.full.helper.SignUp;
@SuppressWarnings("serial")
@Controller
@RequestMapping("/SignUp")
public class SignUpServlet {
	private SignUp signUp;
	@Autowired
	public void setSignUp(SignUp signUp) {
		this.signUp = signUp;
	}

	@ResponseBody
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String  addUser(@RequestBody SignUpDto signUpDto) {
		System.out.println("IAM IN SignUp SERVLET");
		System.out.println(signUpDto);
/*	String userName=req.getParameter("USERNAME");
		String eMail=req.getParameter("EMAIL");
		String password=req.getParameter("PASSWORD");
		String confirmPassword=req.getParameter("CONFIRMPASSWORD");
		SignUpDto signUpDto=new SignUpDto();
	signUpDto.setUSERNAME(userName);
		signUpDto.setEMAIL(eMail);
		signUpDto.setPASSWORD(password);
	    signUpDto.setCONFIRMPASSWORD(confirmPassword); 
	  
	    
		System.out.println(eMail);	
		System.out.println(userName);
		System.out.println(password);
		System.out.println(confirmPassword);*/
		//SignUp signUp=new SignUp();
	 signUpDto.setCustomerId(GenerateUUID.generateUUID());
		signUp.addUser(signUpDto);
		return "CALLING LOGIN.jsp";
		//resp.getWriter().print("CALLING LOGIN.jsp");
		
	
	}
/*	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}*/

}
