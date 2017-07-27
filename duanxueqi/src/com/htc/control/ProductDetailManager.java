package com.htc.control;

import java.util.List;

import com.htc.dao.ProductDetailDAO;
import com.htc.model.BeanProductDetail;
import com.htc.util.BaseException;

public class ProductDetailManager {
	public void createProductDetail(BeanProductDetail d) throws BaseException {
		if(new ProductDetailDAO().getProductDetail(d.getProductID(), d.getRawID())!=null)
			throw new BaseException("该产品对于该原材料的需求已经存在");
		new ProductDetailDAO().createProductDetail(d);
	}

	public void deleteProductDetail(BeanProductDetail d) throws BaseException {
		if (new ProductDetailDAO().getProductDetail(d.getProductID(), d.getRawID()) == null) {
			throw new BaseException("删除项不存在");
		}
		new ProductDetailDAO().deleteProductDetail(d);
	}

	public void modifyProductDetail(BeanProductDetail d) throws BaseException {
		if (new ProductDetailDAO().getProductDetail(d.getProductID(), d.getRawID()) == null) {
			throw new BaseException("修改项不存在");
		}
		new ProductDetailDAO().modifyProductDetail(d);
	}

	public List<BeanProductDetail> loadProductDetail(int productID) throws BaseException {
		return new ProductDetailDAO().qryProductDetail(productID);
	}
}
