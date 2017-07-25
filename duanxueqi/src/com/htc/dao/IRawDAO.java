package com.htc.dao;

import java.util.List;

import com.htc.model.BeanRaw;
import com.htc.util.BaseException;

public interface IRawDAO {
	public void createRaw(BeanRaw r) throws BaseException;
	public void deleteRaw(int rawID) throws BaseException;
	public void modifyRaw(BeanRaw r) throws BaseException;
	public List<BeanRaw> qryRaw(String rawName) throws BaseException;
	public BeanRaw getRaw(int rawID) throws BaseException;
	public List<BeanRaw> supplierRaw(int supplierID) throws BaseException;
	public List<BeanRaw> searchByPrice(double up,double down) throws BaseException;
}
