package com.controller.sellerproduct;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.sellerproduct.ProductJDBC;
import com.dao.sellerproduct.SellerJDBC;
import com.dto.sellerproduct.Seller;

@WebServlet("/SellerLogIn")
public class SellerLogIn extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String email = req.getParameter("inp1");
		String password = req.getParameter("inp2");
		
		SellerJDBC sj = new SellerJDBC();
		ProductJDBC pj = new ProductJDBC();
		HttpSession session = req.getSession();
		
		if(session != null && session.getAttribute("UEmail") != null && session.getAttribute("UPassword") != null)
		{
			try {
				req.setAttribute("products", pj.retriveAllProductData());
				RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
				dispatcher.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			try 
			{
				int resultOut = 0;
				List<Seller> sellerList = sj.allUserList();
				Iterator itr = sellerList.iterator();
				while(itr.hasNext())
				{
					Seller s = (Seller) itr.next();
					if(email.equals(s.getEmail()))
					{
						resultOut = 1;
						if(password.equals(s.getPassword()))
						{
							session.setAttribute("UEmail", email);
							session.setAttribute("UPassword", password);
							req.setAttribute("products", pj.retriveAllProductData());
							RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
							dispatcher.include(req, resp);
						}
						else
						{
							//System.out.println("Password incorrect..");
							//resp.sendRedirect("login.jsp?login=password");
							req.setAttribute("message", "Password Incorrect");
							RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
							dispatcher.forward(req, resp);
						}
					}
				}
				if(resultOut == 0)
				{
					//System.out.println("Entered wrong email..");
					//resp.sendRedirect("login.jsp?login=email");
					req.setAttribute("message", "Email Incorrect");
					RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
					dispatcher.forward(req, resp);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
	}
}
