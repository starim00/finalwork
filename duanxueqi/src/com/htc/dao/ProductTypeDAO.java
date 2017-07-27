package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.htc.model.BeanProductType;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class ProductTypeDAO implements IProductTypeDAO {

	@Override
	public void creatProductType(BeanProductType p) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO producttype(productTypeName,introduction) values (?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getProductTypeName());
			pst.setString(2, p.getIntroduction());
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
	public void deleteProductType(int productTypeID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM producttype WHERE productTypeID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productTypeID);
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
	public void modifyProductType(BeanProductType p) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE producttype SET productTypeName = ?,introduction = ? WHERE productTypeID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getProductTypeName());
			pst.setString(2, p.getIntroduction());
			pst.setInt(3, p.getProductTypeID());
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
	public List<BeanProductType> qryProductType(String productTypeName) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanProductType> product = new ArrayList<BeanProductType>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM producttype WHERE productTypeName like ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + productTypeName + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanProductType p = new BeanProductType();
				p.setProductTypeID(rs.getInt(1));
				p.setProductTypeName(rs.getString(2));
				p.setIntroduction(rs.getString(3));
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
	public BeanProductType getProductType(int productTypeID) throws BaseException {
		BeanProductType p = new BeanProductType();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM producttype WHERE productTypeID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productTypeID);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				p.setProductTypeID(rs.getInt(1));
				p.setProductTypeName(rs.getString(2));
				p.setIntroduction(rs.getString(3));
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
