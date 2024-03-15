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
<form action="SellerLogIn" method="post">
<label>Email</label>
<input type="text" name="inp1"><br>
<label>Password</label>
<input type="password" name="inp2"><br>
<input type="submit" value="Login">
</form>
<% String message = (String)request.getAttribute("message"); %>
<% if(message != null) 
       { %>
     <p style="color: green;"><%= message %></p>
    <% } %>
</body>
</html>