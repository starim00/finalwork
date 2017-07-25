package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.htc.model.BeanProduct;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class ProductDAO implements IProductDAO {

	@Override
	public void createProduct(BeanProduct p) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO product(productName,productPrice,productTypeID,introduction) values (?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getProductName());
			pst.setDouble(2, p.getProductPrice());
			pst.setInt(3, p.getProductTypeID());
			pst.setString(4, p.getIntroduction());
			pst.execute();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public void deleteProduct(int productID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM product WHERE productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,productID);
			pst.execute();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public void modifyProduct(BeanProduct p) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE product SET productName = ?,productPrice = ?,productTypeID = ?,introduction = ? WHERE productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getProductName());
			pst.setDouble(2, p.getProductPrice());
			pst.setInt(3, p.getProductTypeID());
			pst.setString(4, p.getIntroduction());
			pst.setInt(5, p.getProductID());
			pst.execute();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public List<BeanProduct> qryProduct(String productName) throws BaseException {
		ArrayList<BeanProduct> product = new ArrayList<BeanProduct>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM product WHERE productName like ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+productName+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanProduct p = new BeanProduct();
				p.setProductID(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductPrice(rs.getDouble(3));
				p.setProductTypeID(rs.getInt(4));
				p.setIntroduction(rs.getString(5));
				product.add(p);
			}
			rs.close();
			pst.close();
			conn.close();
			return product;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public BeanProduct getProduct(int productID) throws BaseException {
		// TODO Auto-generated method stub
		BeanProduct p = new BeanProduct();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM product WHERE productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productID);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				p.setProductID(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductPrice(rs.getDouble(3));
				p.setProductTypeID(rs.getInt(4));
				p.setIntroduction(rs.getString(5));
				rs.close();
				pst.close();
				conn.close();
				return p;
			}
			else{
				rs.close();
				pst.close();
				conn.close();
				return null;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public List<BeanProduct> productTypeProduct(int productTypeID) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanProduct> product = new ArrayList<BeanProduct>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM product WHERE productTypeID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productTypeID);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanProduct p = new BeanProduct();
				p.setProductID(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductPrice(rs.getDouble(3));
				p.setProductTypeID(rs.getInt(4));
				p.setIntroduction(rs.getString(5));
				product.add(p);
			}
			rs.close();
			pst.close();
			conn.close();
			return product;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public List<BeanProduct> searchByPrice(double up, double down) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanProduct> product = new ArrayList<BeanProduct>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM product WHERE price BETWEEN ? AND ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, down);
			pst.setDouble(2, up);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanProduct p = new BeanProduct();
				p.setProductID(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductPrice(rs.getDouble(3));
				p.setProductTypeID(rs.getInt(4));
				p.setIntroduction(rs.getString(5));
				product.add(p);
			}
			rs.close();
			pst.close();
			conn.close();
			return product;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

}
