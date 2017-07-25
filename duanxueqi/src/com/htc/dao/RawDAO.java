package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.htc.model.BeanRaw;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class RawDAO implements IRawDAO {

	@Override
	public void createRaw(BeanRaw r) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO raw(rawName,price,supplierID,introduction) values (?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, r.getRawName());
			pst.setDouble(2, r.getPrice());
			pst.setInt(3, r.getSupplier());
			pst.setString(4, r.getIntroduction());
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
	public void deleteRaw(int rawID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM raw WHERE rawID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,rawID);
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
	public void modifyRaw(BeanRaw r) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE raw SET rawName = ?,Price = ?,supplierID = ?,introduction = ? WHERE rawID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, r.getRawName());
			pst.setDouble(2, r.getPrice());
			pst.setInt(3, r.getSupplier());
			pst.setString(4, r.getIntroduction());
			pst.setInt(5, r.getRawID());
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
	public List<BeanRaw> qryRaw(String rawName) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanRaw> product = new ArrayList<BeanRaw>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM raw WHERE rawName like ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+rawName+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanRaw p = new BeanRaw();
				p.setRawID(rs.getInt(1));
				p.setRawName(rs.getString(2));
				p.setPrice(rs.getDouble(3));
				p.setSupplier(rs.getInt(4));
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
	public BeanRaw getRaw(int rawID) throws BaseException {
		// TODO Auto-generated method stub
		BeanRaw p = new BeanRaw();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM raw WHERE rawID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rawID);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				p.setRawID(rs.getInt(1));
				p.setRawName(rs.getString(2));
				p.setPrice(rs.getDouble(3));
				p.setSupplier(rs.getInt(4));
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
	public List<BeanRaw> supplierRaw(int supplierID) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanRaw> product = new ArrayList<BeanRaw>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM raw WHERE supplierID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, supplierID);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanRaw p = new BeanRaw();
				p.setRawID(rs.getInt(1));
				p.setRawName(rs.getString(2));
				p.setPrice(rs.getDouble(3));
				p.setSupplier(rs.getInt(4));
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
	public List<BeanRaw> searchByPrice(double up, double down) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanRaw> product = new ArrayList<BeanRaw>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM raw WHERE price BETWEEN ? AND ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, down);
			pst.setDouble(2, up);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanRaw p = new BeanRaw();
				p.setRawID(rs.getInt(1));
				p.setRawName(rs.getString(2));
				p.setPrice(rs.getDouble(3));
				p.setSupplier(rs.getInt(4));
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
