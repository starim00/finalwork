package com.htc.dao;

import com.htc.model.BeanProductStock;
import com.htc.util.BaseException;

public interface IProductStockDAO {
	public void createProductStock(BeanProductStock p) throws BaseException;
	public void deleteProductStock(int productStockID) throws BaseException;
	public void modifyProductStock(BeanProductStock p) throws BaseException;
	public BeanProductStock qryProductStock(int productID) throws BaseException;
	public BeanProductStock getProductStock(int productStockID) throws BaseException;
}
