package com.full;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.helper.CheckUser;

@Controller
@RequestMapping("/check")
public class CheckUserServlet  {
	private CheckUser checkUser;
	@Autowired
	public void setCheckUser(CheckUser checkUser) {
		this.checkUser = checkUser;
	}
	@RequestMapping(value="/CheckUserServlet",method=RequestMethod.GET)
	@ResponseBody
	public  String checkUserServlet(@RequestParam("EMAIL") String eMail,@RequestParam("PASSWORD") String password,HttpServletRequest req)  {
	
		System.out.println(eMail);
		System.out.println(password);
		//CheckUser checkUser=new CheckUser();
		boolean userExists=checkUser.checkUser(eMail, password);
if(userExists){
	System.out.println("USER EXISTS ");
	HttpSession session=req.getSession(true);
	session.setAttribute("EMAIL",eMail);
	session.setAttribute("PASSWORD",password);
	session.setMaxInactiveInterval(120);
	return "true";
}
else{
	System.out.println("USER  NOT EXISTS ");
	return "false";
}
	
	
	}
	
/*	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}*/

}
