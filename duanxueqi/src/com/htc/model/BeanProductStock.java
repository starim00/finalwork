package com.htc.model;

public class BeanProductStock {
	private int productStockID;
	private int productID;
	private String stockAddress;
	private int stockQuantity;

	public int getProductStockID() {
		return productStockID;
	}

	public void setProductStockID(int productStockID) {
		this.productStockID = productStockID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getStockAddress() {
		return stockAddress;
	}

	public void setStockAddress(String stockAddress) {
		this.stockAddress = stockAddress;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}
