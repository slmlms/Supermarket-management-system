package com.ming.dao.bean;
/**
 * 订单详情
 * @author SM22
 * @date  2018年7月10日下午7:51:32
 */
public class OrderDetails {
	private Integer OrderID;	//订单ID
	private Integer ProductID;	//商品ID
	private Integer ProductNum;	//商品数量
	private Double CommodityPrice; // 商品单价

	public Integer getOrderID() {
		return OrderID;
	}

	public void setOrderID(Integer orderID) {
		OrderID = orderID;
	}

	public Integer getProductID() {
		return ProductID;
	}

	public void setProductID(Integer productID) {
		ProductID = productID;
	}

	public Integer getProductNum() {
		return ProductNum;
	}

	public void setProductNum(Integer productNum) {
		ProductNum = productNum;
	}

	public Double getCommodityPrice() {
		return CommodityPrice;
	}

	public void setCommodityPrice(Double commodityPrice) {
		CommodityPrice = commodityPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDetails [OrderID=");
		builder.append(OrderID);
		builder.append(", ProductID=");
		builder.append(ProductID);
		builder.append(", ProductNum=");
		builder.append(ProductNum);
		builder.append(", CommodityPrice=");
		builder.append(CommodityPrice);
		builder.append("]");
		return builder.toString();
	}
}
