package com.controller.sellerproduct;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import com.dao.sellerproduct.ProductJDBC;
import com.dto.sellerproduct.Product;
@WebServlet("/EditProduct")
@MultipartConfig
public class EditProduct extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession();
		if(session != null && session.getAttribute("UEmail") != null && session.getAttribute("UPassword") != null) 
		{
			ProductJDBC pj = new ProductJDBC();
			int productId = Integer.parseInt(req.getParameter("inp1"));
			//System.out.println("Product id is : "+productId);
			String productName = req.getParameter("inp2");
			String productBrand = req.getParameter("inp3");
			int productQuantity = Integer.parseInt(req.getParameter("inp4"));
			double productPrice = Double.parseDouble(req.getParameter("inp5"));
			double productDiscount = Double.parseDouble(req.getParameter("inp6"));
			Part filePart = req.getPart("inp7");
			byte[] productImage = filePart.getInputStream().readAllBytes();
			//byte[] productImage = 
			if(filePart.getSize() > 1)
			{
				productImage = filePart.getInputStream().readAllBytes();
			}
			else
			{
				Product prod;
				try {
					prod = pj.findProductById(productId);
					productImage = prod.getProductImage();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			Product p = new Product();
			p.setId(productId);
			p.setProductName(productName);
			p.setProductBrand(productBrand);
			p.setProductQuantity(productQuantity);
			p.setProductPrice(productPrice);
			p.setProductDiscount(productDiscount);
			p.setProductImage(productImage);
			
			
			try 
			{
				int result = pj.UpdateProduct(p);
				if(result == 1)
				{
					req.setAttribute("message", "Product Modified Successfully");
					req.setAttribute("products", pj.retriveAllProductData());
					RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
					dispatcher.forward(req, resp);
				}
				else
				{
					req.setAttribute("message", "Error to Modify Product");
					RequestDispatcher dispatcher = req.getRequestDispatcher("/editProduct.jsp");
					dispatcher.forward(req, resp);
				}
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			req.setAttribute("message", "Login Required to Edit a Product Information");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
			dispatcher.include(req, resp);
		}
		
	}
}
