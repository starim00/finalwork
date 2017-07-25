package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.htc.model.BeanRawOrder;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class RawOrderDAO implements IRawOrderDAO {

	@Override
	public void createRawOrder(BeanRawOrder r) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO raworder(rawID,quantity,price) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, r.getRawID());
			pst.setInt(2, r.getQuantity());
			pst.setDouble(3, r.getPrice());
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
	public void deleteRawOrder(int rawOrderID) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM raworder WHERE rawOrderID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,rawOrderID);
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
	public void modifyRawOrder(BeanRawOrder r) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "UPDATE raworder SET rawID = ?,quantity = ?,price = ?,isDone = ? WHERE rawOrderID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, r.getRawID());
			pst.setInt(2, r.getQuantity());
			pst.setDouble(3, r.getPrice());
			pst.setBoolean(4, r.isDone());
			pst.setInt(5, r.getRawOrderID());
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
	public List<BeanRawOrder> qryRawOrder(int rawID) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanRawOrder> raw = new ArrayList<BeanRawOrder>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM raworder WHERE rawID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rawID);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BeanRawOrder p = new BeanRawOrder();
				p.setRawOrderID(rs.getInt(1));
				p.setRawID(rs.getInt(2));
				p.setQuantity(rs.getInt(3));
				p.setPrice(rs.getDouble(4));
				p.setDone(rs.getBoolean(5));
				raw.add(p);
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
	public BeanRawOrder getRawOrder(int rawOrderID) throws BaseException {
		// TODO Auto-generated method stub
		BeanRawOrder p = new BeanRawOrder();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM raworder WHERE rawOrderID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rawOrderID);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				p.setRawOrderID(rs.getInt(1));
				p.setRawID(rs.getInt(2));
				p.setQuantity(rs.getInt(3));
				p.setPrice(rs.getDouble(4));
				p.setDone(rs.getBoolean(5));
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
