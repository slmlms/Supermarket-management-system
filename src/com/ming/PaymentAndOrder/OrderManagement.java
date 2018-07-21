package com.ming.PaymentAndOrder;

import java.util.Date;
import java.util.Map;

import com.ming.dao.bean.OrderDetails;
import com.ming.dao.bean.OrderInformation;
import com.ming.dao.bean.SupermarketProduct;
import com.ming.dao.impl.SupermarketDaoImpl;
import com.ming.dao.impl.SupermarketDaoImplUpdate;

/**
 * 订单管理
 * 
 * @author SM22
 * @date 2018年7月10日下午7:37:52
 */
public class OrderManagement {
	OrderInformation oi = new OrderInformation();
	OrderDetails od = new OrderDetails();
	Map<SupermarketProduct, Integer> map = PurchaseManagement.spmap;
	
	// 创建订单信息
	public void CreateOrder(Integer UserID, Double AmountOfOrders, String PaymentTypes) {
		Date date = new Date(System.currentTimeMillis());
		Integer orderNum =(int) (System.currentTimeMillis()/1000-999999999);		//生成订单号
		oi.setOrderID(orderNum);
		oi.setUserID(UserID);
		oi.setAmountOfOrders(AmountOfOrders);
		oi.setPaymentTypes(PaymentTypes);
		oi.setOrderTime(date);
		
		new SupermarketDaoImplUpdate().add(oi);
		
		// 创建订单详情
	for (SupermarketProduct sp1 : map.keySet()) {
			od.setOrderID(orderNum);
			od.setProductID(sp1.getProductNumber());
			od.setCommodityPrice(sp1.getUnitPrice() * sp1.getCount() / 10);
			od.setProductNum(map.get(sp1));
			// new SupermarketDaoImplUpdate().add(od);
		}
		new SupermarketDaoImpl().add(od);
		
		
	}

	
	

}
