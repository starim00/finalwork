package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.htc.model.BeanProductStock;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class ProductStockDAO implements IProductStockDAO {

	@Override
	public void createProductStock(BeanProductStock p) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO productstock(productID,stockAddress,stockQuantity) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, p.getProductID());
			pst.setString(2, p.getStockAddress());
			pst.setInt(3, p.getStockQuantity());
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
	public void deleteProductStock(int productStockID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM productstock WHERE productStockID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productStockID);
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
	public void modifyProductStock(BeanProductStock p) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE productstock SET productID = ?,stockAddress = ? ,stockQuantity = ? WHERE productStockID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, p.getProductID());
			pst.setString(2, p.getStockAddress());
			pst.setInt(3, p.getStockQuantity());
			pst.setInt(4, p.getProductStockID());
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
	public BeanProductStock qryProductStock(int productID) throws BaseException {
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM productstock WHERE productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productID);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				BeanProductStock p = new BeanProductStock();
				p.setProductStockID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setStockAddress(rs.getString(3));
				p.setStockQuantity(rs.getInt(4));
				rs.close();
				pst.close();
				conn.close();
				return p;
			} else {
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
	public BeanProductStock getProductStock(int productStockID) throws BaseException {
		BeanProductStock p = new BeanProductStock();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM productstock WHERE productStockID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productStockID);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				p.setProductStockID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setStockAddress(rs.getString(3));
				p.setStockQuantity(rs.getInt(4));
				rs.close();
				pst.close();
				conn.close();
				return p;
			} else {
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

}
