package com.htc.dao;

import java.util.List;

import com.htc.model.BeanProductDetail;
import com.htc.util.BaseException;

public interface IProductDetailDAO {
	public void createProductDetail(BeanProductDetail d) throws BaseException;
	public void deleteProductDetail(BeanProductDetail d) throws BaseException;
	public void modifyProductDetail(BeanProductDetail d) throws BaseException;
	public List<BeanProductDetail> qryProductDetail(int productID) throws BaseException;
	public BeanProductDetail getProductDetail(int productID,int rawID) throws BaseException;
}
