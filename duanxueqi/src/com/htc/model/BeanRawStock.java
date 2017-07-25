package com.htc.model;

public class BeanRawStock {
	private int rawStockID;
	private int rawID;
	private String stockAddress;
	private int stockQuantity;
	public int getRawStockID() {
		return rawStockID;
	}
	public void setRawStockID(int rawStockID) {
		this.rawStockID = rawStockID;
	}
	public int getRawID() {
		return rawID;
	}
	public void setRawID(int rawID) {
		this.rawID = rawID;
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
