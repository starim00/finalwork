package com.htc.dao;

import java.util.List;

import com.htc.model.BeanRawStorage;
import com.htc.util.BaseException;

public interface IRawStorageDAO {
	public void createRawStorage(BeanRawStorage r) throws BaseException;
	public void deleteRawStorage(int rawStorageID) throws BaseException;
	public void modifyRawStorage(BeanRawStorage r) throws BaseException;
	public List<BeanRawStorage> qryRawStorage(int rawID) throws BaseException;
	public BeanRawStorage getRawStorage(int rawStorageID) throws BaseException;
}
