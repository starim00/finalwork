package com.htc.dao;

import java.util.List;

import com.htc.model.BeanProductType;
import com.htc.util.BaseException;

public interface IProductTypeDAO {
	public void creatProductType(BeanProductType p) throws BaseException;
	public void deleteProductType(int productTypeID) throws BaseException;
	public void modifyProductType(BeanProductType p) throws BaseException;
	public List<BeanProductType> qryProductType(String productTypeName) throws BaseException;
	public BeanProductType getProductType(int productTypeID) throws BaseException;
}
