<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.dao.sellerproduct.*" %>
<%@ page import="com.dto.sellerproduct.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' media='screen' href='style.css'>
</head>
<body>
<form action="addProduct.jsp" method="post">
<label>Add Product</label>
<input type="submit" value="Add">
</form>
<% String message = (String)request.getAttribute("message"); %>
<% if(message != null) 
       { %>
     <p style="color: green;"><%= message %></p>
    <% } %>
<table border="1">
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Brand</th>
		<th>Quantity</th>
		<th>Price</th>
		<th>Discount</th>
		<th>Image</th>
		<th>Delete</th>
		<th>Edit</th>
		
	</tr>
<% ProductJDBC pj = new ProductJDBC(); %>
<% List<Product> productList = pj.retriveAllProductData(); %>
<% Iterator itr = productList.iterator(); %>
<% while(itr.hasNext()) {
   Product p = (Product)itr.next(); %>
   <tr>
   		<td><%=p.getId() %></td>
   		<td><%=p.getProductName() %></td>
   		<td><%=p.getProductBrand() %></td>
   		<td><%=p.getProductQuantity() %></td>
   		<td><%=p.getProductPrice() %></td>
   		<td><%=p.getProductDiscount() %></td>
   		<td><img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(p.getProductImage()) %>" width="100px" height="100px" alt="Image"></td>
   		<td>
        <a href="DeleteProduct?pId=<%= p.getId() %>">Delete</a>
    	</td>
    	<td>
        <a href="editProduct.jsp?pId=<%= p.getId() %>">Edit</a>
    	</td>
   		<!--  
   		<td>
   					<form action="DeleteProduct" method="post">
                        <input type="hidden" name="pId" value="<%= p.getId() %>">
                        <input type="submit" value="Delete">
                    </form>
   		
   		</td>
   		<td>
   					<form action="editProduct.jsp" method="post">
                        <input type="hidden" name="pId" value="<%= p.getId() %>">
                        <input type="submit" value="Edit">
                    </form>
   		
   		</td>
   		
   		-->
   </tr>
<%} %>
</table>

<form action="SellerLogout" method="post">
<input type="submit" value="LogOut">
</form>
</body>
</html>