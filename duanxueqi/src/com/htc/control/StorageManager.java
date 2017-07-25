package com.htc.control;

import java.util.ArrayList;
import java.util.List;

import com.htc.dao.ProductStorageDAO;
import com.htc.dao.RawDAO;
import com.htc.dao.RawStorageDAO;
import com.htc.model.BeanProductStorage;
import com.htc.model.BeanRaw;
import com.htc.model.BeanRawStorage;
import com.htc.util.BaseException;

public class StorageManager {
	public List<BeanRawStorage> loadAllRawStorage(int rawID) throws BaseException {
		return new RawStorageDAO().qryRawStorage(rawID);
	}

	public List<BeanRawStorage> searchBySupplier(int supplierID) throws BaseException {
		List<BeanRaw> br = new RawDAO().supplierRaw(supplierID);
		if (br.isEmpty()) {
			throw new BaseException("该供货商没有提供原材料");
		} else {
			ArrayList<BeanRawStorage> brs = new ArrayList<BeanRawStorage>();
			for (int i = 0; i < br.size(); i++) {
				brs.addAll(new RawStorageDAO().qryRawStorage(br.get(i).getRawID()));
			}
			return brs;
		}
	}

	public List<BeanProductStorage> loadAllProductStorage(int productID) throws BaseException {
		return new ProductStorageDAO().qryProductStorage(productID);
	}
}
