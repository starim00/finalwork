package com.htc.control;

import java.util.List;

import com.htc.dao.CustomerDAO;
import com.htc.dao.ProductOrderDAO;
import com.htc.model.BeanCustomer;
import com.htc.model.BeanProductOrder;
import com.htc.util.BaseException;

public class CustomerManager {
	public void creatCustomer(BeanCustomer c) throws BaseException {
		new CustomerDAO().createCustomer(c);
	}

	public void deleteCustomer(BeanCustomer c, boolean d) throws BaseException {
		if (new CustomerDAO().getCustomer(c.getCustomerID()) == null)
			throw new BaseException("客户不存在");
		if (!new ProductOrderDAO().customerProductOrder(c.getCustomerID()).isEmpty()) {
			if (d == true) {
				List<BeanProductOrder> l = new ProductOrderDAO().customerProductOrder(c.getCustomerID());
				ProductOrderDAO p = new ProductOrderDAO();
				for (int i = 0; i < l.size(); i++) {
					p.deleteProductOrder(l.get(i).getProductOrderID());
				}
				new CustomerDAO().deleteCustomer(c.getCustomerID());
			} else {
				throw new BaseException("客户还有订单未删除");
			}
		} else {
			new CustomerDAO().deleteCustomer(c.getCustomerID());
		}
	}

	public List<BeanCustomer> loadAllCustomer() throws BaseException {
		return new CustomerDAO().qryCustomer("");
	}

	public void modifyCustomer(BeanCustomer c) throws BaseException {
		if (new CustomerDAO().getCustomer(c.getCustomerID()) == null)
			throw new BaseException("客户不存在");
		new CustomerDAO().modifyCustomer(c);
	}
}
