package com.htc.dao;

import java.util.List;

import com.htc.model.BeanProduct;
import com.htc.util.BaseException;

public interface IProductDAO {
	public void createProduct(BeanProduct p) throws BaseException;

	public void deleteProduct(int productID) throws BaseException;

	public void modifyProduct(BeanProduct p) throws BaseException;

	public List<BeanProduct> qryProduct(String productName) throws BaseException;

	public BeanProduct getProduct(int productID) throws BaseException;

	public List<BeanProduct> productTypeProduct(int productTypeID) throws BaseException;

	public List<BeanProduct> searchByPrice(double up, double down) throws BaseException;

	public List<BeanProduct> searchProduct(String productName, int productTypeID) throws BaseException;
}
