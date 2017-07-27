package com.htc.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.htc.dao.ProduceDAO;
import com.htc.dao.ProductDAO;
import com.htc.dao.ProductDetailDAO;
import com.htc.dao.ProductStockDAO;
import com.htc.dao.ProductStorageDAO;
import com.htc.dao.RawStockDAO;
import com.htc.dao.RawStorageDAO;
import com.htc.model.BeanProduce;
import com.htc.model.BeanProduct;
import com.htc.model.BeanProductDetail;
import com.htc.model.BeanProductStock;
import com.htc.model.BeanProductStorage;
import com.htc.model.BeanRawStock;
import com.htc.model.BeanRawStorage;
import com.htc.util.BaseException;

public class ProduceManager {
	public void produce(int productID, int quantity) throws BaseException {
		List<BeanProductDetail> d = new ProductDetailDAO().qryProductDetail(productID);
		BeanProduct p = new ProductDAO().getProduct(productID);
		if (p == null) {
			throw new BaseException("�ò�Ʒ������");
		}
		if (d.isEmpty()) {
			throw new BaseException("û�������䷽�������");
		}
		int i = 0;
		ArrayList<BeanRawStock> r = new ArrayList<>();
		for (i = 0; i < d.size(); i++) {
			r.add(new RawStockDAO().qryRawStock(d.get(i).getRawID()));
			if (r.get(i).getStockQuantity() < (d.get(i).getQuantity() * quantity)) {
				break;
			}
		}
		if (i < d.size()) {
			throw new BaseException("ԭ���ϱ��" + d.get(i).getRawID() + "��ԭ���ϲ���");
		} else {
			for (i = 0; i < r.size(); i++) {
				BeanRawStorage rs = new BeanRawStorage();
				rs.setRawID(d.get(i).getRawID());
				rs.setStorageQuantity(-d.get(i).getQuantity() * quantity);
				rs.setData(new Date(System.currentTimeMillis()));
				new RawStorageDAO().createRawStorage(rs);
				r.get(i).setStockQuantity(r.get(i).getStockQuantity() - (d.get(i).getQuantity() * quantity));
				new RawStockDAO().modifyRawStock(r.get(i));
			}
			BeanProductStorage pst = new BeanProductStorage();
			pst.setProductID(productID);
			pst.setStorageQuantity(quantity);
			pst.setDate(new Date(System.currentTimeMillis()));
			new ProductStorageDAO().createProductStorage(pst);
			BeanProductStock ps = new ProductStockDAO().qryProductStock(productID);
			ps.setStockQuantity(ps.getStockQuantity() + quantity);
			new ProductStockDAO().modifyProductStock(ps);
			BeanProduce bp = new BeanProduce();
			bp.setProductID(productID);
			bp.setQuantity(quantity);
			bp.setDate(new Date(System.currentTimeMillis()));
			new ProduceDAO().createProduce(bp);
		}
	}

	public List<BeanProduce> loadAllProduce() throws BaseException {
		return new ProduceDAO().loadAllProduce();
	}

	public List<BeanProduce> loadByProductID(int productID) throws BaseException {
		if (new ProductDAO().getProduct(productID) == null) {
			throw new BaseException("��Ʒ������");
		} else {
			return new ProduceDAO().loadByProduct(productID);
		}
	}

	public List<BeanProduce> loadByDate(Date up, Date down) throws BaseException {
		if (up.getTime() < down.getTime()) {
			throw new BaseException("ʱ�����޲���С������");
		} else {
			return new ProduceDAO().loadByDate(up.getTime(), down.getTime());
		}
	}

	public List<BeanProduce> loadByDate(Date up, Date down, int productID) throws BaseException {
		if (up.getTime() < down.getTime()) {
			throw new BaseException("ʱ�����޲���С������");
		} else {
			return new ProduceDAO().loadByDate(productID, up.getTime(), down.getTime());
		}
	}
}
