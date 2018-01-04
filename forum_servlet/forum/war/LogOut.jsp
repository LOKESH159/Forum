<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<%
System.out.print("IAM IN HOMEPAGE1.jsp");
HttpSession session=request.getSession(false);
if(session!=null){	
	System.out.print("IAM INVALIDATING THE SESSION");
	session.invalidate();
	response.sendRedirect("/homepage.jsp");
}
else{
	response.sendRedirect("/homepage.jsp");
}
	
%>