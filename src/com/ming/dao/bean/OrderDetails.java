package com.ming.dao.bean;
/**
 * ��������
 * @author SM22
 * @date  2018��7��10������7:51:32
 */
public class OrderDetails {
	private Integer OrderID;	//����ID
	private Integer ProductID;	//��ƷID
	private Integer ProductNum;	//��Ʒ����
	private Double CommodityPrice; // ��Ʒ����

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
