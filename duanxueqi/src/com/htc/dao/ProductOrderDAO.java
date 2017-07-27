package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.htc.model.BeanProductOrder;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class ProductOrderDAO implements IProductOrderDAO {

	@Override
	public void createProductOrder(BeanProductOrder d) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO productorder(productID,customerID,quantity) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, d.getProductID());
			pst.setInt(2, d.getCustomerID());
			pst.setInt(3, d.getQuantity());
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
	public void deleteProductOrder(int productOrderID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM productorder WHERE productOrderID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productOrderID);
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
	public void modifyProductOrder(BeanProductOrder d) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE productorder SET productID = ?,customerID= ?,quantity = ?,isdone = ? WHERE productOrderID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, d.getProductID());
			pst.setInt(2, d.getCustomerID());
			pst.setInt(3, d.getQuantity());
			pst.setBoolean(4, d.isDone());
			pst.setInt(5, d.getProductOrderID());
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
	public List<BeanProductOrder> qryProductOrder(int productID) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanProductOrder> product = new ArrayList<BeanProductOrder>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM productorder";
			if(productID!=0)
				sql+=" WHERE productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			if(productID!=0)
				pst.setInt(1, productID);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanProductOrder p = new BeanProductOrder();
				p.setProductOrderID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setCustomerID(rs.getInt(3));
				p.setQuantity(rs.getInt(4));
				p.setDone(rs.getBoolean(5));
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
	public BeanProductOrder getProductOrder(int productOrderID) throws BaseException {
		// TODO Auto-generated method stub
		BeanProductOrder p = new BeanProductOrder();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM productorder WHERE productOrderID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productOrderID);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				p.setProductOrderID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setCustomerID(rs.getInt(3));
				p.setQuantity(rs.getInt(4));
				p.setDone(rs.getBoolean(5));
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
	public List<BeanProductOrder> customerProductOrder(int customerID) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanProductOrder> product = new ArrayList<BeanProductOrder>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM productorder WHERE customerID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, customerID);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanProductOrder p = new BeanProductOrder();
				p.setProductOrderID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setCustomerID(rs.getInt(3));
				p.setQuantity(rs.getInt(4));
				p.setDone(rs.getBoolean(5));
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
