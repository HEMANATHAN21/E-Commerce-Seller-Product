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
<form action="SellerSignUp" method="post">
<label>Id</label>
<input type="text" name="inp1"><br>
<label>Name</label>
<input type="text" name="inp2"><br>
<label>Email</label>
<input type="text" name="inp3"><br>
<label>Password</label>
<input type="password" name="inp4"><br>
<label>Contact</label>
<input type="text" name="inp5"><br>
<input type="submit" value="SignUp">
</form>
<% String message = (String)request.getAttribute("message"); %>
<% if(message != null) 
       { %>
     <p style="color: green;"><%= message %></p>
    <% } %>
</body>
</html>