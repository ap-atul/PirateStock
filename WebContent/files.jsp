<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='java.util.*'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pirate Stock</title>
</head>
<body>
	<%
	ArrayList<String> files = (ArrayList<String>) request.getAttribute("files");
	String path = (String) request.getParameter("path");
	
	if (path == null)
		path = "";
	
	if(files != null){
		for(String file: files){
			%>
			<a href="Fetch?path=<%= path + file + "/" %>"><%=file %></a><br>
	
		<% }
	}else{
		out.println("null");
	}
	%>
</body>
</html>