package com.htc.model;

public class BeanProductOrder {
	private int productOrderID;
	private int productID;
	private int customerID;
	private int quantity;
	private boolean isDone;

	public int getProductOrderID() {
		return productOrderID;
	}

	public void setProductOrderID(int productOrderID) {
		this.productOrderID = productOrderID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}
