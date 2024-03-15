package com.dao.sellerproduct;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import com.dto.sellerproduct.Product;


public class ProductJDBC 
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
	
	public int addProduct(Product p) throws SQLException
	{
		Connection con = ProductJDBC.db();
		String query = "insert into product values(?,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, p.getId());
		pst.setString(2, p.getProductName());
		pst.setString(3, p.getProductBrand());
		pst.setInt(4, p.getProductQuantity());
		pst.setDouble(5, p.getProductPrice());
		pst.setDouble(6, p.getProductDiscount());
		//pst.setBytes(7, p.getProductImage());
		Blob image = new SerialBlob(p.getProductImage());
		pst.setBlob(7, image);
		int result = pst.executeUpdate();
		con.close();
		return result;
		
	}
	
	public int UpdateProduct(Product p) throws SQLException
	{
		Connection con = ProductJDBC.db();
		String query = "update product set pname = ?, pbrand = ?, pquantity = ?, pprice = ?, pdiscount = ?, pimage = ? where pid = ?";
		
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, p.getProductName());
		pst.setString(2, p.getProductBrand());
		pst.setInt(3, p.getProductQuantity());
		pst.setDouble(4, p.getProductPrice());
		pst.setDouble(5, p.getProductDiscount());
		//pst.setBytes(6, p.getProductImage());
		Blob image = new SerialBlob(p.getProductImage());
		pst.setBlob(6, image);
		pst.setInt(7, p.getId());
		int result = pst.executeUpdate();
		con.close();
		return result;
	}
	public Product findProductById(int id) throws SQLException, IOException
	{
		Connection con = ProductJDBC.db();
		String query = "select * from product where pid =?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		Product p = new Product();
		if(rs.next())
		{
			p.setId(rs.getInt(1));
			p.setProductName(rs.getString(2));
			p.setProductBrand(rs.getString(3));
			p.setProductQuantity(rs.getInt(4));
			p.setProductPrice(rs.getDouble(5));
			p.setProductDiscount(rs.getDouble(6));
			Blob image = rs.getBlob(7);
			byte[] img = image.getBinaryStream().readAllBytes();
			p.setProductImage(img);
		}
		con.close();
		return p;
	}
	
	public int deleteProduct(int id) throws SQLException
	{
		Connection con = ProductJDBC.db();
		String query = "delete from product where pid = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		int result = pst.executeUpdate();
		con.close();
		return result;
	}
	
	public List<Product> retriveAllProductData() throws SQLException, IOException
	{
		Connection con = ProductJDBC.db();
		String query = "select * from product ";
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		List<Product> productList = new ArrayList<Product>();
		while(rs.next())
		{
			Product p = new Product();
			p.setId(rs.getInt(1));
			p.setProductName(rs.getString(2));
			p.setProductBrand(rs.getString(3));
			p.setProductQuantity(rs.getInt(4));
			p.setProductPrice(rs.getDouble(5));
			p.setProductDiscount(rs.getDouble(6));
			//p.setProductImage(rs.getBytes(7));
			//Method 1
			Blob image = rs.getBlob(7);
			byte[] img = image.getBinaryStream().readAllBytes();
			p.setProductImage(img);
			//method 2
			/*Blob image = rs.getBlob(7);
			int blobiImgLength = (int) image.length();
			byte[] img = image.getBytes(1, blobiImgLength);*/
			
			productList.add(p);
		}
		con.close();
		return productList;
	}
	
	public void deleteProductById(int id) throws SQLException
	{
		Connection con = ProductJDBC.db();
		String query = "delete from product where pid = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		pst.executeUpdate();
		con.close();
	}
}
