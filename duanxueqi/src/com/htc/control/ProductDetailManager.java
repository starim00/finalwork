package com.htc.control;

import java.util.List;

import com.htc.dao.ProductDetailDAO;
import com.htc.model.BeanProductDetail;
import com.htc.util.BaseException;

public class ProductDetailManager {
	public void createProductDetail(BeanProductDetail d) throws BaseException {
		if(new ProductDetailDAO().getProductDetail(d.getProductID(), d.getRawID())!=null)
			throw new BaseException("�ò�Ʒ���ڸ�ԭ���ϵ������Ѿ�����");
		new ProductDetailDAO().createProductDetail(d);
	}

	public void deleteProductDetail(BeanProductDetail d) throws BaseException {
		if (new ProductDetailDAO().getProductDetail(d.getProductID(), d.getRawID()) == null) {
			throw new BaseException("ɾ�������");
		}
		new ProductDetailDAO().deleteProductDetail(d);
	}

	public void modifyProductDetail(BeanProductDetail d) throws BaseException {
		if (new ProductDetailDAO().getProductDetail(d.getProductID(), d.getRawID()) == null) {
			throw new BaseException("�޸������");
		}
		new ProductDetailDAO().modifyProductDetail(d);
	}

	public List<BeanProductDetail> loadProductDetail(int productID) throws BaseException {
		return new ProductDetailDAO().qryProductDetail(productID);
	}
}
