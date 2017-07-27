package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.htc.model.BeanCustomer;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class CustomerDAO implements ICustomerDAO {

	@Override
	public void createCustomer(BeanCustomer c) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO customer(customerName,customerAddress,contactPerson) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, c.getCustomerName());
			pst.setString(2, c.getCustomerAddress());
			pst.setString(3, c.getContactPerson());
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
	public void deleteCustomer(int customerID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM customer WHERE customerID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, customerID);
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
	public void modifyCustomer(BeanCustomer c) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE customer SET customerName = ?,customerAddress = ?,contactPerson = ? WHERE customerID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, c.getCustomerName());
			pst.setString(2, c.getCustomerAddress());
			pst.setString(3, c.getContactPerson());
			pst.setInt(4, c.getCustomerID());
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
	public List<BeanCustomer> qryCustomer(String customerName) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanCustomer> customers = new ArrayList<BeanCustomer>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM customer WHERE customerName like ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + customerName + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanCustomer c = new BeanCustomer();
				c.setCustomerID(rs.getInt(1));
				c.setCustomerName(rs.getString(2));
				c.setCustomerAddress(rs.getString(3));
				c.setContactPerson(rs.getString(4));
				customers.add(c);
			}
			rs.close();
			pst.close();
			conn.close();
			return customers;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public BeanCustomer getCustomer(int customerID) throws BaseException {
		// TODO Auto-generated method stub
		BeanCustomer c = new BeanCustomer();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM customer WHERE customerID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, customerID);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				c.setCustomerID(rs.getInt(1));
				c.setCustomerName(rs.getString(2));
				c.setCustomerAddress(rs.getString(3));
				c.setContactPerson(rs.getString(4));
				rs.close();
				pst.close();
				conn.close();
				return c;
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
