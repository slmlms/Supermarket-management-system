package com.ming.dao.bean;

import java.util.Date;

/**
 * 订单信息页
 * 
 * @author SM22
 * @date 2018年7月10日下午7:48:14
 */
public class OrderInformation {
	private Integer OrderID; // 订单ID
	private Integer UserID; // 用户ID
	private Double AmountOfOrders; // 订单金额
	private Date OrderTime; // 下单时间
	private String PaymentTypes; // 下单类型
	private Integer ProductID;	//商品ID

	public Integer getOrderID() {
		return OrderID;
	}

	public void setOrderID(Integer orderID) {
		OrderID = orderID;
	}

	public Integer getUserID() {
		return UserID;
	}

	public void setUserID(Integer userID) {
		UserID = userID;
	}

	public Double getAmountOfOrders() {
		return AmountOfOrders;
	}

	public void setAmountOfOrders(Double amountOfOrders) {
		AmountOfOrders = amountOfOrders;
	}

	public Date getOrderTime() {
		return OrderTime;
	}

	public void setOrderTime(Date orderTime) {
		OrderTime = orderTime;
	}

	public String getPaymentTypes() {
		return PaymentTypes;
	}

	public void setPaymentTypes(String paymentTypes) {
		PaymentTypes = paymentTypes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderInformation [OrderID=");
		builder.append(OrderID);
		builder.append(", UserID=");
		builder.append(UserID);
		builder.append(", AmountOfOrders=");
		builder.append(AmountOfOrders);
		builder.append(", OrderTime=");
		builder.append(OrderTime);
		builder.append(", PaymentTypes=");
		builder.append(PaymentTypes);
		builder.append("]");
		return builder.toString();
	}

	public Integer getProductID() {
		return ProductID;
	}

	public void setProductID(Integer productID) {
		ProductID = productID;
	}
}
