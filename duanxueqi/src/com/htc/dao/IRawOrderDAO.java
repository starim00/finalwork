package com.htc.dao;

import java.util.List;

import com.htc.model.BeanRawOrder;
import com.htc.util.BaseException;

public interface IRawOrderDAO {
	public void createRawOrder(BeanRawOrder r) throws BaseException;
	public void deleteRawOrder(int rawOrderID) throws BaseException;
	public void modifyRawOrder(BeanRawOrder r) throws BaseException;
	public List<BeanRawOrder> qryRawOrder(int rawID) throws BaseException;
	public BeanRawOrder getRawOrder(int rawOrderID) throws BaseException;
}
