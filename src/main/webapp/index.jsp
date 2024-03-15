<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' media='screen' href='style.css'>
</head>
<body>
<h1>Seller Product</h1>
<a href="signup.jsp">SignUp</a><br>
<a href="SessionCheck">Login</a><br>
<% String message = (String)request.getAttribute("message"); %>
<% if(message != null) 
       { %>
     <p style="color: green;"><%= message %></p>
    <% } %>

</body>
</html>