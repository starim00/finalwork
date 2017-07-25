package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.htc.model.BeanRawStorage;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class RawStorageDAO implements IRawStorageDAO {

	@Override
	public void createRawStorage(BeanRawStorage r) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO rawstorage(rawID,date,storageQuantity) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, r.getRawID());
			pst.setLong(2, r.getDate().getTime());
			pst.setInt(3, r.getStorageQuantity());
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
	public void deleteRawStorage(int rawStorageID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM rawstorage WHERE rawStorageID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,rawStorageID);
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
	public void modifyRawStorage(BeanRawStorage r) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE rawstorage SET rawID = ?, date = ?, storageQuantity = ? WHERE rawStorageID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, r.getRawID());
			pst.setLong(2, r.getDate().getTime());
			pst.setInt(3, r.getStorageQuantity());
			pst.setInt(4, r.getRawStorageID());
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
	public List<BeanRawStorage> qryRawStorage(int rawID) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanRawStorage> raw = new ArrayList<BeanRawStorage>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM rawstorage WHERE rawID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rawID);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanRawStorage r = new BeanRawStorage();
				r.setRawStorageID(rs.getInt(1));
				r.setRawID(rs.getInt(2));
				r.setData(new Date(rs.getLong(3)));
				r.setStorageQuantity(rs.getInt(4));
				raw.add(r);
			}
			rs.close();
			pst.close();
			conn.close();
			return raw;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public BeanRawStorage getRawStorage(int rawStorageID) throws BaseException {
		// TODO Auto-generated method stub
		BeanRawStorage p = new BeanRawStorage();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM rawstorage WHERE rawStorageID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rawStorageID);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				p.setRawStorageID(rs.getInt(1));
				p.setRawID(rs.getInt(2));
				p.setData(new Date(rs.getLong(3)));
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
