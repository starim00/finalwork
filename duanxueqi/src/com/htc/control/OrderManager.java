package com.htc.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.htc.dao.ProductOrderDAO;
import com.htc.dao.ProductStockDAO;
import com.htc.dao.ProductStorageDAO;
import com.htc.dao.RawDAO;
import com.htc.dao.RawOrderDAO;
import com.htc.dao.RawStockDAO;
import com.htc.dao.RawStorageDAO;
import com.htc.model.BeanProductOrder;
import com.htc.model.BeanProductStock;
import com.htc.model.BeanProductStorage;
import com.htc.model.BeanRaw;
import com.htc.model.BeanRawOrder;
import com.htc.model.BeanRawStock;
import com.htc.model.BeanRawStorage;
import com.htc.util.BaseException;

public class OrderManager {
	public void createRawOrder(BeanRawOrder o) throws BaseException {
		new RawOrderDAO().createRawOrder(o);
	}

	public void deleteRawOrder(BeanRawOrder o) throws BaseException {
		new RawOrderDAO().deleteRawOrder(o.getRawOrderID());
	}

	public void doneRawOrder(BeanRawOrder o) throws BaseException {
		BeanRawOrder o1 = new RawOrderDAO().getRawOrder(o.getRawOrderID());
		if (o1.isDone()) {
			throw new BaseException("该订单已完成");
		} else {
			BeanRawStock sto = new RawStockDAO().qryRawStock(o1.getRawID());
			if(sto==null)
				throw new BaseException("无该商品库存");
			if (sto.getStockQuantity() < -o1.getQuantity() || sto == null) {
				throw new BaseException("库存数量不足，无法完成订单");
			} else {
				BeanRawStorage rs = new BeanRawStorage();
				rs.setRawID(o1.getRawID());
				rs.setStorageQuantity(o1.getQuantity());
				rs.setData(new Date(System.currentTimeMillis()));
				new RawStorageDAO().createRawStorage(rs);
				sto.setStockQuantity(sto.getStockQuantity() + o1.getQuantity());
				new RawStockDAO().modifyRawStock(sto);
				o1.setDone(true);
				new RawOrderDAO().modifyRawOrder(o1);
			}
		}
	}

	public void modifyRawOrder(BeanRawOrder o) throws BaseException {
		BeanRawOrder o1 = new RawOrderDAO().getRawOrder(o.getRawOrderID());
		if (o1 == null) {
			throw new BaseException("订单不存在");
		}
		if (o1.isDone()) {
			throw new BaseException("订单已完成，不能修改");
		}
		if (o1.isDone() == o.isDone()) {
			new RawOrderDAO().modifyRawOrder(o);
		} else {
			throw new BaseException("非法操作");
		}
	}

	public List<BeanRawOrder> loadAllRawOrder(int rawID) throws BaseException {
		return new RawOrderDAO().qryRawOrder(rawID);
	}

	public List<BeanRawOrder> loadBySupplier(int supplierID) throws BaseException {
		List<BeanRaw> lr = new RawDAO().supplierRaw(supplierID);
		if (lr.isEmpty()) {
			throw new BaseException("该供货商没有提供原材料");
		} else {
			ArrayList<BeanRawOrder> bro = new ArrayList<BeanRawOrder>();
			for (int i = 0; i < lr.size(); i++) {
				bro.addAll(new RawOrderDAO().qryRawOrder(lr.get(i).getRawID()));
			}
			return bro;
		}
	}

	public void createProductOrder(BeanProductOrder o) throws BaseException {
		new ProductOrderDAO().createProductOrder(o);
	}

	public void deleteProductOrder(BeanProductOrder o) throws BaseException {
		new ProductOrderDAO().deleteProductOrder(o.getProductOrderID());
	}

	public void doneProductOrder(BeanProductOrder o) throws BaseException {
		BeanProductOrder o1 = new ProductOrderDAO().getProductOrder(o.getProductOrderID());
		if (o1.isDone()) {
			throw new BaseException("该订单已完成");
		} else {
			BeanProductStock sto = new ProductStockDAO().qryProductStock(o1.getProductID());
			if (sto.getStockQuantity() < o1.getQuantity() || sto == null) {
				throw new BaseException("库存数量不足，无法完成订单");
			} else {
				BeanProductStorage rs = new BeanProductStorage();
				rs.setProductID(o1.getProductID());
				rs.setStorageQuantity(o1.getQuantity());
				rs.setProductOrderID(o1.getProductOrderID());
				rs.setDate(new Date(System.currentTimeMillis()));
				new ProductStorageDAO().createProductStorage(rs);
				sto.setStockQuantity(sto.getStockQuantity() - o1.getQuantity());
				new ProductStockDAO().modifyProductStock(sto);
				o1.setDone(true);
				new ProductOrderDAO().modifyProductOrder(o1);
			}
		}
	}

	public void modifyProductOrder(BeanProductOrder o) throws BaseException {
		BeanProductOrder o1 = new ProductOrderDAO().getProductOrder(o.getProductOrderID());
		if (o1 == null) {
			throw new BaseException("订单不存在");
		}
		if (o1.isDone()) {
			throw new BaseException("订单已完成，不能修改");
		}
		if (o1.isDone() == o.isDone()) {
			new ProductOrderDAO().modifyProductOrder(o);
		} else {
			throw new BaseException("非法操作");
		}
	}

	public List<BeanProductOrder> loadAllProductOrder(int productID) throws BaseException {
		return new ProductOrderDAO().qryProductOrder(productID);
	}

	public List<BeanProductOrder> loadByCustomer(int customerID) throws BaseException {
		List<BeanProductOrder> bpo = new ProductOrderDAO().customerProductOrder(customerID);
		if (bpo.isEmpty()) {
			throw new BaseException("该客户没有订单");
		} else {
			return bpo;
		}
	}

}
