package com.htc.dao;

import java.util.List;

import com.htc.model.BeanSupplier;
import com.htc.util.BaseException;

public interface ISupplierDAO {
	public void creatSupplier(BeanSupplier s) throws BaseException;
	public void deleteSupplier(int supplierID) throws BaseException;
	public void modifySupplier(BeanSupplier s) throws BaseException;
	public List<BeanSupplier> qrySupplier(String supplierName) throws BaseException;
	public BeanSupplier getSupplier(int supplierID) throws BaseException;
}
