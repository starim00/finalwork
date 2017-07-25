package com.htc.dao;

import com.htc.model.BeanRawStock;
import com.htc.util.BaseException;

public interface IRawStockDAO {
	public void createRawStock(BeanRawStock r) throws BaseException;

	public void deleteRawStock(int rawStockID) throws BaseException;

	public void modifyRawStock(BeanRawStock r) throws BaseException;

	public BeanRawStock qryRawStock(int rawID) throws BaseException;

	public BeanRawStock getRawStock(int rawStockID) throws BaseException;
}
