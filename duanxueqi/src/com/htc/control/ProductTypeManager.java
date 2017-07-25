package com.htc.control;

import java.util.List;

import com.htc.dao.ProductDAO;
import com.htc.dao.ProductTypeDAO;
import com.htc.model.BeanProductType;
import com.htc.util.BaseException;

public class ProductTypeManager {
	public void createProductType(BeanProductType p) throws BaseException {
		new ProductTypeDAO().creatProductType(p);
	}

	public void deleteProductType(BeanProductType p) throws BaseException {
		if (new ProductTypeDAO().getProductType(p.getProductTypeID()) == null) {
			throw new BaseException("�����ڵĲ�Ʒ����");
		}
		if (new ProductDAO().productTypeProduct(p.getProductTypeID()).isEmpty()) {
			throw new BaseException("���и����͵Ĳ�Ʒ����");
		}
		new ProductTypeDAO().deleteProductType(p.getProductTypeID());
	}

	public void modifyProductType(BeanProductType p) throws BaseException {
		if (new ProductTypeDAO().getProductType(p.getProductTypeID()) == null) {
			throw new BaseException("�����ڵĲ�Ʒ����");
		}
		new ProductTypeDAO().modifyProductType(p);
	}

	public List<BeanProductType> loadAllProductType() throws BaseException {
		return new ProductTypeDAO().qryProductType("");
	}
}
