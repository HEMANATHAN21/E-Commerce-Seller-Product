package com.controller.sellerproduct;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SellerLogout")
public class SellerLogout extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("UEmail") != null && session.getAttribute("UPassword") != null)
		{
			session.invalidate();
			req.setAttribute("message", "Logout Successfully");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
			dispatcher.include(req, resp);
		}
		else
		{
			resp.getWriter().print("Session not found");
		}
		
	}

}
