package com.controller.sellerproduct;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.sellerproduct.ProductJDBC;

@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int id = Integer.parseInt(req.getParameter("pId"));
		//System.out.println("Deleting id is : "+id);
		ProductJDBC pj = new ProductJDBC();
		HttpSession session = req.getSession();
		if(session != null && session.getAttribute("UEmail") != null && session.getAttribute("UPassword") != null) 
		{
			try 
			{
				int result = pj.deleteProduct(id);
				if(result == 1)
				{
					req.setAttribute("message", "One Product Deleted");					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
					dispatcher.include(req, resp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{
			req.setAttribute("message", "Login Required to Delete a Product ");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
			dispatcher.include(req, resp);
		}
	}
}
