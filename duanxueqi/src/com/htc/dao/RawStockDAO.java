package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.htc.model.BeanRawStock;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class RawStockDAO implements IRawStockDAO {

	@Override
	public void createRawStock(BeanRawStock r) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO rawstock(rawID,stockAddress,stockQuantity) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, r.getRawID());
			pst.setString(2, r.getStockAddress());
			pst.setInt(3, r.getStockQuantity());
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
	public void deleteRawStock(int rawStockID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM rawstock WHERE rawStockID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rawStockID);
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
	public void modifyRawStock(BeanRawStock r) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE rawstock SET rawID = ?,stockAddress = ? ,stockQuantity = ? WHERE rawStockID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, r.getRawID());
			pst.setString(2, r.getStockAddress());
			pst.setInt(3, r.getStockQuantity());
			pst.setInt(4, r.getRawStockID());
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
	public BeanRawStock qryRawStock(int rawID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM rawstock WHERE rawID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rawID);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				BeanRawStock p = new BeanRawStock();
				p.setRawStockID(rs.getInt(1));
				p.setRawID(rs.getInt(2));
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
	public BeanRawStock getRawStock(int rawStockID) throws BaseException {
		// TODO Auto-generated method stub
		BeanRawStock p = new BeanRawStock();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM rawstock WHERE rawStockID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rawStockID);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				p.setRawStockID(rs.getInt(1));
				p.setRawID(rs.getInt(2));
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
