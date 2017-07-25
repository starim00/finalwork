package com.htc.dao;

import java.util.List;

import com.htc.model.BeanProductOrder;
import com.htc.util.BaseException;

public interface IProductOrderDAO {
	public void createProductOrder(BeanProductOrder d) throws BaseException;
	public void deleteProductOrder(int productOrderID) throws BaseException;
	public void modifyProductOrder(BeanProductOrder d) throws BaseException;
	public List<BeanProductOrder> qryProductOrder(int productID) throws BaseException;
	public BeanProductOrder getProductOrder(int productOrderID) throws BaseException;
	public List<BeanProductOrder> customerProductOrder(int customerID) throws BaseException;
}
