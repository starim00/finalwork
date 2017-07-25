package com.htc.model;

public class BeanRawOrder {
	private int rawOrderID;
	private int rawID;
	private int quantity;
	private double price;
	private boolean isDone;
	public int getRawOrderID() {
		return rawOrderID;
	}
	public void setRawOrderID(int rawOrderID) {
		this.rawOrderID = rawOrderID;
	}
	public int getRawID() {
		return rawID;
	}
	public void setRawID(int rawID) {
		this.rawID = rawID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
}
