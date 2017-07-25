package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.htc.model.BeanSupplier;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class SupplierDAO implements ISupplierDAO {

	@Override
	public void creatSupplier(BeanSupplier s) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO supplier(supplierName,supplierAddress,contactPerson,telephone,introduction) values (?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, s.getSupplierName());
			pst.setString(2, s.getSupplierAddress());
			pst.setString(3, s.getContactPerson());
			pst.setInt(4, s.getTelephone());
			pst.setString(5, s.getIntroduction());
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
	public void deleteSupplier(int supplierID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM supplier WHERE supplierID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,supplierID);
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
	public void modifySupplier(BeanSupplier s) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE supplier SET supplierName = ?,supplierAddress = ?,contactPerson = ?,telephone = ?,introduction = ? WHERE customerID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, s.getSupplierName());
			pst.setString(2, s.getSupplierAddress());
			pst.setString(3, s.getContactPerson());
			pst.setInt(4, s.getTelephone());
			pst.setString(5, s.getIntroduction());
			pst.setInt(6, s.getSupplierID());
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
	public List<BeanSupplier> qrySupplier(String supplierName) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanSupplier> customers = new ArrayList<BeanSupplier>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM supplier WHERE supplierName like ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+supplierName+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanSupplier c = new BeanSupplier();
				c.setSupplierID(rs.getInt(1));
				c.setSupplierName(rs.getString(2));
				c.setSupplierAddress(rs.getString(3));
				c.setContactPerson(rs.getString(4));
				c.setTelephone(rs.getInt(5));
				c.setIntroduction(rs.getString(6));
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
	public BeanSupplier getSupplier(int supplierID) throws BaseException {
		// TODO Auto-generated method stub
		BeanSupplier c = new BeanSupplier();
		try {
			Connection conn =DBUtil.getConnection();
			String sql = "SELECT * FROM supplier WHERE supplierID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, supplierID);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				c.setSupplierID(rs.getInt(1));
				c.setSupplierName(rs.getString(2));
				c.setSupplierAddress(rs.getString(3));
				c.setContactPerson(rs.getString(4));
				c.setTelephone(rs.getInt(5));
				c.setIntroduction(rs.getString(6));
				rs.close();
				pst.close();
				conn.close();
				return c;
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

}
