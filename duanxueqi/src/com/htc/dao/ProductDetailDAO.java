package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.htc.model.BeanProductDetail;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class ProductDetailDAO implements IProductDetailDAO {

	@Override
	public void createProductDetail(BeanProductDetail d) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO productdetail(productID,rawID,quantity) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, d.getProductID());
			pst.setDouble(2, d.getRawID());
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
	public void deleteProductDetail(BeanProductDetail d) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM productdetail WHERE productID = ? AND rawID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,d.getProductID());
			pst.setInt(2, d.getRawID());
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
	public void modifyProductDetail(BeanProductDetail d) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE productdetail SET quantity = ? WHERE productID = ? AND rawID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, d.getQuantity());
			pst.setInt(2, d.getProductID());
			pst.setInt(3, d.getRawID());
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
	public List<BeanProductDetail> qryProductDetail(int productID) throws BaseException {
		ArrayList<BeanProductDetail> product = new ArrayList<BeanProductDetail>();
		try {
			Connection conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM productdetail";
			if(productID!=0)
				sql+=" WHERE¡¡productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			if(productID!=0)
				pst.setInt(1, productID);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanProductDetail p = new BeanProductDetail();
				p.setProductID(rs.getInt(1));
				p.setRawID(rs.getInt(2));
				p.setQuantity(rs.getInt(3));
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
	public BeanProductDetail getProductDetail(int productID ,int rawID) throws BaseException {
		// TODO Auto-generated method stub
		BeanProductDetail p = new BeanProductDetail();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM productdetail WHERE productID = ? AND rawID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productID);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				p.setProductID(rs.getInt(1));
				p.setRawID(rs.getInt(2));
				p.setQuantity(rs.getInt(3));
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
