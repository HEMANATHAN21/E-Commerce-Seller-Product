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
<form action="AddProduct" method="post" enctype="multipart/form-data">
<label>Product Id </label>
<input type="text" name="inp1"><br>
<label>Product Name </label>
<input type="text" name="inp2"><br>
<label>Product Brand </label>
<input type="text" name="inp3"><br>
<label>Product Quantity </label>
<input type="text" name="inp4"><br>
<label>Product Price</label>
<input type="text" name="inp5"><br>
<label>Product Discount </label>
<input type="text" name="inp6"><br>
<label>Product Image  </label>
<input type="file" name="inp7" id="imageInput" onchange="displayImage(this)">
    <img id="selectedImage" style="max-width: 300px; max-height: 300px;" alt="Selected Image"><br>
<input type="submit" value="Add">
</form>
<% String message = (String)request.getAttribute("message"); %>
<% if(message != null) 
       { %>
     <p style="color: green;"><%= message %></p>
    <% } %>
 
<script>
    function displayImage(input) {
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