package com.htc.control;

import java.util.List;

import com.htc.dao.RawDAO;
import com.htc.dao.RawOrderDAO;
import com.htc.dao.RawStockDAO;
import com.htc.dao.RawStorageDAO;
import com.htc.model.BeanRaw;
import com.htc.model.BeanRawOrder;
import com.htc.model.BeanRawStorage;
import com.htc.util.BaseException;

public class RawManager {
	public void createRaw(BeanRaw r) throws BaseException {
		new RawDAO().createRaw(r);
	}

	public void deleteRaw(BeanRaw r, boolean d) throws BaseException {
		int flag1 = 0, flag2 = 0;
		if(new RawStockDAO().qryRawStock(r.getRawID())!=null){
			throw new BaseException("该原料还有库存");
		}
		if (!new RawOrderDAO().qryRawOrder(r.getRawID()).isEmpty()) {
			flag1 = 1;
		}
		if (!new RawStorageDAO().qryRawStorage(r.getRawID()).isEmpty()) {
			flag2 = 1;
		}
		if ((flag1 == 1 || flag2 == 1) && d) {
			if (flag1 == 1) {
				List<BeanRawOrder> o = new RawOrderDAO().qryRawOrder(r.getRawID());
				for (int i = 0; i < o.size(); i++) {
					new RawOrderDAO().deleteRawOrder(o.get(i).getRawOrderID());
				}
			}
			if (flag2 == 1) {
				List<BeanRawStorage> o = new RawStorageDAO().qryRawStorage(r.getRawID());
				for (int i = 0; i < o.size(); i++) {
					new RawStorageDAO().deleteRawStorage(o.get(i).getRawStorageID());
				}
			}
			new RawDAO().deleteRaw(r.getRawID());
		}
		else if(flag1 == 0 && flag2 == 0){
			new RawDAO().deleteRaw(r.getRawID());
		}
		else{
			throw new BaseException("该原材料还有订单或出入库记录");
		}
	}
	public void modifyRaw(BeanRaw r) throws BaseException{
		if(new RawDAO().getRaw(r.getRawID())==null){
			throw new BaseException("不存在的原材料");
		}
		new RawDAO().modifyRaw(r);
	}
	public List<BeanRaw> loadAllRaw() throws BaseException{
		return new RawDAO().qryRaw("");
	}
	public List<BeanRaw> searchByName(String rawName) throws BaseException{
		return new RawDAO().qryRaw(rawName);
	}
	public List<BeanRaw> searchByPrice(double up,double down) throws BaseException{
		return new RawDAO().searchByPrice(up, down);
	}
	public BeanRaw searchByID(int rawID) throws BaseException{
		if(new RawDAO().getRaw(rawID)==null){
			throw new BaseException("该ID不存在");
		}
		else{
			return new RawDAO().getRaw(rawID);
		}
	}
}
