package com.controller.sellerproduct;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.sellerproduct.ProductJDBC;
import com.dto.sellerproduct.Product;

@WebServlet("/AddProduct")
@MultipartConfig
public class AddProduct extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int productId = Integer.parseInt(req.getParameter("inp1"));
		String productName = req.getParameter("inp2");
		String productBrand = req.getParameter("inp3");
		int productQuantity = Integer.parseInt(req.getParameter("inp4"));
		double productPrice = Double.parseDouble(req.getParameter("inp5"));
		double productDiscount = Double.parseDouble(req.getParameter("inp6"));
		Part filePart = req.getPart("inp7");
		byte[] productImage = filePart.getInputStream().readAllBytes();
		
		Product p = new Product();
		p.setId(productId);
		p.setProductName(productName);
		p.setProductBrand(productBrand);
		p.setProductQuantity(productQuantity);
		p.setProductPrice(productPrice);
		p.setProductDiscount(productDiscount);
		p.setProductImage(productImage);
		
		ProductJDBC pj = new ProductJDBC();
		
		try {
			int result = pj.addProduct(p);
			if(result == 1)
			{
				//resp.sendRedirect("home.jsp?add=successful");
				req.setAttribute("products", pj.retriveAllProductData());
				req.setAttribute("message", "Product Added Successfully");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
				dispatcher.forward(req, resp);
			}
			else
			{
				//resp.sendRedirect("addProduct.jsp?add=failed");
				req.setAttribute("message", "Error to Add Product");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/addProduct.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
