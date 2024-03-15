<%@page import="com.dao.sellerproduct.ProductJDBC"%>
<%@page import="com.dto.sellerproduct.Product"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' media='screen' href='style.css'>
</head>
<body>
<%int id = Integer.parseInt(request.getParameter("pId")); %>
<%ProductJDBC pj = new ProductJDBC();
Product p = pj.findProductById(id);
%>
<form action="EditProduct" method="post" enctype="multipart/form-data">
<label>Product Id  </label>
<input type="text" name="inp1" value="<%= p.getId()%>" readonly><br>
<label>Product Name  </label>
<input type="text" name="inp2" value="<%= p.getProductName()%>"><br>
<label>Product Brand  </label>
<input type="text" name="inp3" value="<%= p.getProductBrand()%>"><br>
<label>Product Quantity  </label>
<input type="text" name="inp4" value="<%= p.getProductQuantity()%>"><br>
<label>Product Price  </label>
<input type="text" name="inp5" value="<%= p.getProductPrice()%>"><br>
<label>Product Discount  </label>
<input type="text" name="inp6" value="<%= p.getProductDiscount()%>"><br>
<label>Product Image  </label>
<input type="file" name="inp7" id="imageInput" onchange="displayNewImage(this)">
	<img id="selectedImage" src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(p.getProductImage()) %>" width="100px" height="100px" alt="Image">
	<br>
<input type="submit" value="Modify">
</form>

<% String message = (String)request.getAttribute("message"); %>
<% if(message != null) 
       { %>
     <p style="color: green;"><%= message %></p>
    <% } %>
    
<script>
    function displayNewImage(input) {
        var imageElement = document.getElementById("selectedImage");
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                imageElement.src = e.target.result;
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>