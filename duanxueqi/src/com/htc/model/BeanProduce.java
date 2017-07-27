package com.htc.model;

import java.util.Date;

public class BeanProduce {
	private int produceID;
	private int productID;
	private Date date;
	private int quantity;
	public int getProduceID() {
		return produceID;
	}
	public void setProduceID(int produceID) {
		this.produceID = produceID;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
