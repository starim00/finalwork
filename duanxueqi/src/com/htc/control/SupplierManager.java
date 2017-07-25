package com.htc.control;

import java.util.List;

import com.htc.dao.RawDAO;
import com.htc.dao.SupplierDAO;
import com.htc.model.BeanSupplier;
import com.htc.util.BaseException;

public class SupplierManager {
	public void createSupplier(BeanSupplier s) throws BaseException {
		new SupplierDAO().creatSupplier(s);
	}

	public void deleteSupplier(BeanSupplier s) throws BaseException {
		if (new SupplierDAO().getSupplier(s.getSupplierID()) == null) {
			throw new BaseException("供货商不存在");
		}
		if (new RawDAO().supplierRaw(s.getSupplierID()).isEmpty()) {
			throw new BaseException("供货商有原材料未删除");
		}
		new SupplierDAO().deleteSupplier(s.getSupplierID());
	}

	public void modifySupplier(BeanSupplier s) throws BaseException {
		if (new SupplierDAO().getSupplier(s.getSupplierID()) == null) {
			throw new BaseException("供货商不存在");
		}
		new SupplierDAO().modifySupplier(s);
	}

	public List<BeanSupplier> loadAllSupplier() throws BaseException {
		return new SupplierDAO().qrySupplier("");
	}
}
