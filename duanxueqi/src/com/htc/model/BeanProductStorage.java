package com.htc.model;

import java.util.Date;

public class BeanProductStorage {
	private int productStorageID;
	private int productID;
	private Date date;
	public int getProductStorageID() {
		return productStorageID;
	}
	public void setProductStorageID(int productStorageID) {
		this.productStorageID = productStorageID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getStorageQuantity() {
		return storageQuantity;
	}
	public void setStorageQuantity(int storageQuantity) {
		this.storageQuantity = storageQuantity;
	}
	private int storageQuantity;
}
