package com.htc.dao;

import java.util.List;

import com.htc.model.BeanCustomer;
import com.htc.util.BaseException;

public interface ICustomerDAO {
	public void createCustomer(BeanCustomer c) throws BaseException;
	public void deleteCustomer(int customerID) throws BaseException;
	public void modifyCustomer(BeanCustomer c) throws BaseException;
	public List<BeanCustomer> qryCustomer(String customerName) throws BaseException;
	public BeanCustomer getCustomer(int customerID) throws BaseException;
}
