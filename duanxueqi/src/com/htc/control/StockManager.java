package com.htc.control;

import java.util.ArrayList;
import java.util.List;

import com.htc.dao.ProductStockDAO;
import com.htc.dao.RawDAO;
import com.htc.dao.RawStockDAO;
import com.htc.model.BeanProductStock;
import com.htc.model.BeanRaw;
import com.htc.model.BeanRawStock;
import com.htc.util.BaseException;

public class StockManager {
	public void createRawStock(BeanRawStock s) throws BaseException {
		new RawStockDAO().createRawStock(s);
	}

	public void changeRawQuantity(int rawID, int storageQuantity) throws BaseException {
		BeanRawStock s = new RawStockDAO().qryRawStock(rawID);
		if (s == null) {
			throw new BaseException("该原材料没有库存");
		} else {
			s.setStockQuantity(s.getStockQuantity() + storageQuantity);
			new RawStockDAO().modifyRawStock(s);
		}
	}

	public void changeRawStockName(int rawStockID, String stockName) throws BaseException {
		BeanRawStock s = new RawStockDAO().getRawStock(rawStockID);
		if (s == null) {
			throw new BaseException("该库存不存在");
		} else {
			s.setStockAddress(stockName);
			new RawStockDAO().modifyRawStock(s);
		}
	}

	public void deleteRawStock(BeanRawStock s) throws BaseException {
		if (new RawStockDAO().getRawStock(s.getRawStockID()) == null) {
			throw new BaseException("该库存不存在");
		} else {
			new RawStockDAO().deleteRawStock(s.getRawStockID());
		}
	}

	public BeanRawStock searchRawStock(int rawID) throws BaseException {
		return new RawStockDAO().qryRawStock(rawID);
	}

	public List<BeanRawStock> searchBySupplier(int supplierID) throws BaseException{
		List<BeanRaw> br = new RawDAO().supplierRaw(supplierID);
		if(br.isEmpty()){
			throw new BaseException("该供应商没有供应原材料");
		}
		else{
			ArrayList<BeanRawStock> brs = new ArrayList<BeanRawStock>();
			for(int i = 0;i<br.size();i++){
				brs.add(new RawStockDAO().qryRawStock(br.get(i).getRawID()));
			}
			return brs;
		}
	}
	
	public void createProductStock(BeanProductStock s) throws BaseException {
		new ProductStockDAO().createProductStock(s);
	}

	public void changeProductQuantity(int productID, int storageQuantity) throws BaseException {
		BeanProductStock s = new ProductStockDAO().qryProductStock(productID);
		if (s == null) {
			throw new BaseException("该原材料没有库存");
		} else {
			s.setStockQuantity(s.getStockQuantity() + storageQuantity);
			new ProductStockDAO().modifyProductStock(s);
		}
	}

	public void changeProductStockName(int productStockID, String stockName) throws BaseException {
		BeanProductStock s = new ProductStockDAO().getProductStock(productStockID);
		if (s == null) {
			throw new BaseException("该库存不存在");
		} else {
			s.setStockAddress(stockName);
			new ProductStockDAO().modifyProductStock(s);
		}
	}

	public void deleteProductStock(BeanProductStock s) throws BaseException {
		if (new ProductStockDAO().getProductStock(s.getProductStockID()) == null) {
			throw new BaseException("该库存不存在");
		} else {
			new ProductStockDAO().deleteProductStock(s.getProductStockID());
		}
	}

	public BeanProductStock searchProductStock(int productID) throws BaseException {
		return new ProductStockDAO().qryProductStock(productID);
	}
}
