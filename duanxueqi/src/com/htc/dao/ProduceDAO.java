package com.htc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.htc.model.BeanProduce;
import com.htc.util.BaseException;
import com.htc.util.DBUtil;
import com.htc.util.DbException;

public class ProduceDAO implements IProduceDAO {

	@Override
	public void createProduce(BeanProduce p) throws BaseException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "INSERT INTO produce(productID,date,quantity) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, p.getProductID());
			pst.setLong(2, p.getDate().getTime());
			pst.setInt(3, p.getQuantity());
			pst.execute();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	public void deleteProduce(int productID) throws BaseException {
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "DELETE FROM produce WHERE productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productID);
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
	public List<BeanProduce> loadAllProduce() throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanProduce> produce = new ArrayList<BeanProduce>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM produce";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanProduce p = new BeanProduce();
				p.setProduceID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setDate(new Date(rs.getLong(3)));
				p.setQuantity(rs.getInt(4));
				produce.add(p);
			}
			rs.close();
			pst.close();
			conn.close();
			return produce;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public List<BeanProduce> loadByProduct(int productID) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanProduce> produce = new ArrayList<BeanProduce>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM produce WHERE productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, productID);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanProduce p = new BeanProduce();
				p.setProduceID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setDate(new Date(rs.getLong(3)));
				p.setQuantity(rs.getInt(4));
				produce.add(p);
			}
			rs.close();
			pst.close();
			conn.close();
			return produce;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public List<BeanProduce> loadByDate(long up, long down) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanProduce> produce = new ArrayList<BeanProduce>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM produce WHERE date BETWEEN ? AND ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setLong(1, down);
			pst.setLong(2, up);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanProduce p = new BeanProduce();
				p.setProduceID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setDate(new Date(rs.getLong(3)));
				p.setQuantity(rs.getInt(4));
				produce.add(p);
			}
			rs.close();
			pst.close();
			conn.close();
			return produce;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	@Override
	public List<BeanProduce> loadByDate(int productID, long up, long down) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanProduce> produce = new ArrayList<BeanProduce>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "SELECT * FROM produce WHERE (date BETWEEN ? AND ?) AND productID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setLong(1, down);
			pst.setLong(2, up);
			pst.setInt(3, productID);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanProduce p = new BeanProduce();
				p.setProduceID(rs.getInt(1));
				p.setProductID(rs.getInt(2));
				p.setDate(new Date(rs.getLong(3)));
				p.setQuantity(rs.getInt(4));
				produce.add(p);
			}
			rs.close();
			pst.close();
			conn.close();
			return produce;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
	}

}
