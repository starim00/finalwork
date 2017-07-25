package com.htc.dao;

import java.util.List;

import com.htc.model.BeanProductStorage;
import com.htc.util.BaseException;

public interface IProductStorageDAO {
	public void createProductStorage(BeanProductStorage d) throws BaseException;
	public void deleteProductStorage(int productStorageID) throws BaseException;
	public void modifyProductStorage(BeanProductStorage d) throws BaseException;
	public List<BeanProductStorage> qryProductStorage(int productID) throws BaseException;
	public BeanProductStorage getProductStorage(int productStorageID) throws BaseException;
}
