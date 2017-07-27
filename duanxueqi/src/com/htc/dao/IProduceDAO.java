package com.htc.dao;

import java.util.List;

import com.htc.model.BeanProduce;
import com.htc.util.BaseException;

public interface IProduceDAO {
	public void createProduce(BeanProduce p) throws BaseException;
	public List<BeanProduce> loadAllProduce() throws BaseException;
	public List<BeanProduce> loadByProduct(int productID) throws BaseException;
	public List<BeanProduce> loadByDate(long up,long down) throws BaseException;
	public List<BeanProduce> loadByDate(int productID,long up,long down) throws BaseException;
}
