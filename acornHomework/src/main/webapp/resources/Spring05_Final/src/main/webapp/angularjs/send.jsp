<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = request.getParameter("msg");
	
	System.out.println("ajax에서 전송된 msg : "+msg);
%>
{"msg" : "<%=msg %>","test" : "test"}
