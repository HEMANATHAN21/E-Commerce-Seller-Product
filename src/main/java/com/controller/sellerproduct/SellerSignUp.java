package com.controller.sellerproduct;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.sellerproduct.SellerJDBC;
import com.dto.sellerproduct.Seller;
@WebServlet("/SellerSignUp")
public class SellerSignUp extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int id = Integer.parseInt(req.getParameter("inp1"));
		String name = req.getParameter("inp2");
		String email = req.getParameter("inp3");
		String password = req.getParameter("inp4");
		int contact = Integer.parseInt(req.getParameter("inp5"));
		
		Seller s = new Seller();
		s.setId(id);
		s.setName(name);
		s.setEmail(email);
		s.setPassword(password);
		s.setContact(contact);
		SellerJDBC sj = new SellerJDBC();
		try 
		{
			int result = sj.sellerSignUp(s);
			if(result == 1)
			{
				//resp.sendRedirect("index.jsp?signup=success");
				req.setAttribute("message", "SignUp Success");
				RequestDispatcher dispacher = req.getRequestDispatcher("index.jsp");
				dispacher.forward(req, resp);
			}
			else
			{
				//resp.sendRedirect("signup.jsp?signup=Error");
				req.setAttribute("message", "SignUp Failed");
				RequestDispatcher dispacher = req.getRequestDispatcher("index.jsp");
				dispacher.forward(req, resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
