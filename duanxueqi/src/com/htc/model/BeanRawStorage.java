package com.htc.model;

import java.util.Date;

public class BeanRawStorage {
	private int rawStorageID;
	private int rawID;
	private Date date;
	private int storageQuantity;

	public int getRawStorageID() {
		return rawStorageID;
	}

	public void setRawStorageID(int rawStorageID) {
		this.rawStorageID = rawStorageID;
	}

	public int getRawID() {
		return rawID;
	}

	public void setRawID(int rawID) {
		this.rawID = rawID;
	}

	public Date getDate() {
		return date;
	}

	public void setData(Date date) {
		this.date = date;
	}

	public int getStorageQuantity() {
		return storageQuantity;
	}

	public void setStorageQuantity(int storageQuantity) {
		this.storageQuantity = storageQuantity;
	}
}
