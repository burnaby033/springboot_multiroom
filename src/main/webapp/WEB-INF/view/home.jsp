<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>    
<%@page import="com.dms.chat.ChatRoom"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대기방</title>
</head>
<body>
<%
	Collection<ChatRoom> col = (Collection<ChatRoom>) request.getAttribute("collection");
	Iterator<ChatRoom> it = col.iterator();	
	while(it.hasNext()) {		
		String roomId = it.next().getId();
%>
<p>
<div><a href="room?id=<%=roomId%>"><%=roomId%></a></div>
</p>
<%} %>
</body>
</html>