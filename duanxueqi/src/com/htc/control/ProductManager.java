package com.htc.control;

import java.util.List;

import com.htc.dao.ProductDAO;
import com.htc.dao.ProductDetailDAO;
import com.htc.dao.ProductOrderDAO;
import com.htc.dao.ProductStockDAO;
import com.htc.dao.ProductStorageDAO;
import com.htc.model.BeanProduct;
import com.htc.model.BeanProductOrder;
import com.htc.model.BeanProductStorage;
import com.htc.util.BaseException;

public class ProductManager {
	public void createProduct(BeanProduct p) throws BaseException {
		new ProductDAO().createProduct(p);
	}

	public void deleteProduct(BeanProduct p, boolean d) throws BaseException {
		int flag1 = 0, flag2 = 0;
		if(new ProductStockDAO().qryProductStock(p.getProductID())!=null)
			throw new BaseException("该产品还有库存");
		if (!new ProductOrderDAO().qryProductOrder(p.getProductID()).isEmpty()) {
			flag1 = 1;
		}
		if (!new ProductStorageDAO().qryProductStorage(p.getProductID()).isEmpty()) {
			flag2 = 1;
		}
		if ((flag1 == 1 || flag2 == 1) && d) {
			if (flag1 == 1) {
				List<BeanProductOrder> o = new ProductOrderDAO().qryProductOrder(p.getProductID());
				for (int i = 0; i < o.size(); i++) {
					new ProductOrderDAO().deleteProductOrder(o.get(i).getProductOrderID());
				}
			}
			if (flag2 == 1) {
				List<BeanProductStorage> o = new ProductStorageDAO().qryProductStorage(p.getProductID());
				for (int i = 0; i < o.size(); i++) {
					new ProductStorageDAO().deleteProductStorage(o.get(i).getProductStorageID());
				}
			}
			new ProductDAO().deleteProduct(p.getProductID());
		} else if (flag1 == 0 && flag2 == 0) {
			new ProductDetailDAO().deleteByProductID(p.getProductID());
			new ProductDAO().deleteProduct(p.getProductID());
		} else {
			throw new BaseException("该产品还有订单和出入库记录");
		}
	}

	public void modifyProduct(BeanProduct p) throws BaseException {
		if (new ProductDAO().getProduct(p.getProductID()) == null) {
			throw new BaseException("该产品不存在");
		}
		new ProductDAO().modifyProduct(p);
	}

	public List<BeanProduct> loadAllProduct() throws BaseException {
		return new ProductDAO().qryProduct("");
	}

	public List<BeanProduct> searchByName(String productName) throws BaseException {
		return new ProductDAO().qryProduct(productName);
	}

	public List<BeanProduct> searchByPrice(double up, double down) throws BaseException {
		return new ProductDAO().searchByPrice(up, down);
	}

	public BeanProduct searchByID(int productID) throws BaseException {
		if (new ProductDAO().getProduct(productID) == null) {
			throw new BaseException("该ID不存在");
		} else {
			return new ProductDAO().getProduct(productID);
		}
	}
	
	public List<BeanProduct> searchProduct(String productName, int productTypeID) throws BaseException {
		return new ProductDAO().searchProduct(productName, productTypeID);
	}
	
}
