package com.dao.sellerproduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dto.sellerproduct.Seller;

public class SellerJDBC 
{
	static Connection db()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql:///sellerproduct?user=root&password=solohema");
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return con;
		}
		
	}
	
	public int sellerSignUp(Seller s) throws SQLException
	{
		Connection con = SellerJDBC.db();
		String query = "insert into seller values(?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, s.getId());
		pst.setString(2, s.getName());
		pst.setString(3, s.getEmail());
		pst.setString(4, s.getPassword());
		pst.setLong(5, s.getContact());
		int result = pst.executeUpdate();
		con.close();
		return result;
	}
	
	public boolean sellerLogin(Seller s) throws SQLException
	{
		Connection con = SellerJDBC.db();
		String query = "select * from seller where email=? and password=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, s.getEmail());
		pst.setString(2, s.getPassword());
		ResultSet rs = pst.executeQuery();
		con.close();
		if(rs.next())
			return true;
		else
			return false;
	}
	
	public Seller findByEmail(String email) throws SQLException
	{
		Connection con = SellerJDBC.db();
		String query = "select * from seller";
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		Seller s = new Seller();
		if(rs.next())
		{
			s.setId(rs.getInt(1));
			s.setName(rs.getString(2));
			s.setEmail(rs.getString(3));
			s.setPassword(rs.getString(4));
			s.setContact(rs.getLong(5));
		}
		con.close();
		return s;
	}
	
	public List<Seller> allUserList() throws SQLException
	{
		Connection con = SellerJDBC.db();
		String query = "select * from seller";
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		List<Seller> sellerList = new ArrayList<Seller>();
		while(rs.next())
		{
			Seller s = new Seller();
			s.setId(rs.getInt(1));
			s.setName(rs.getString(2));
			s.setEmail(rs.getString(3));
			s.setPassword(rs.getString(4));
			s.setContact(rs.getLong(5));
			sellerList.add(s);
		}
		con.close();
		return sellerList;
	}
}
