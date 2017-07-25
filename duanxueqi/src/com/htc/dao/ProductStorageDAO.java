package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.htc.model.BeanProductStorage;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class ProductStorageDAO implements IProductStorageDAO {

	@Override
	public void createProductStorage(BeanProductStorage d) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO productstorage(productID,date,storageQuantity) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, d.getProductID());
			pst.setLong(2, d.getDate().getTime());
			pst.setInt(3, d.getStorageQuantity());
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
	public void deleteProductStorage(int productStorageID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM productstorage WHERE productStorageID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,productStorageID);
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
	public void modifyProductStorage(BeanProductStorage d) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE productstorage SET productID = ?, date = ?, storageQuantity = ? WHERE productStorageID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, d.getProductID());
			pst.setLong(2, d.getDate().getTime());
			pst.setInt(3, d.getStorageQuantity());
			pst.setInt(4, d.getProductStorageID());
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
	public List<BeanProductStorage> qryProductStorage(int productID) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanProductStorage> product = new ArrayList<BeanProductStorage>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM productstorage WHERE productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productID);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanProductStorage p = new BeanProductStorage();
				p.setProductStorageID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setDate(new Date(rs.getLong(3)));
				p.setStorageQuantity(rs.getInt(4));
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
	public BeanProductStorage getProductStorage(int productStorageID) throws BaseException {
		// TODO Auto-generated method stub
		BeanProductStorage p = new BeanProductStorage();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM productstorage WHERE productStorageID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productStorageID);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				p.setProductStorageID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setDate(new Date(rs.getLong(3)));
				p.setStorageQuantity(rs.getInt(4));
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

}
