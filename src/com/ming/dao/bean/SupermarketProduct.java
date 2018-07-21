package com.ming.dao.bean;


public class SupermarketProduct {
	/**
	 * 商品信息
	 */
	private Integer ProductNumber; // 商品编号
	private String ProductName; // 商品名称
	private Integer ProductTypes; // 商品类型ID
	private Integer NumberOfProducts;// 商品数量
	private Double UnitPrice; // 单价
	private Double Count; // 折扣

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public Integer getNumberOfProducts() {
		return NumberOfProducts;
	}

	public void setNumberOfProducts(Integer numberOfProducts) {
		NumberOfProducts = numberOfProducts;
	}

	public Double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		UnitPrice = unitPrice;
	}

	public Double getCount() {
		return Count;
	}

	public void setCount(Double count) {
		Count = count;
	}

	public Integer getProductNumber() {
		return ProductNumber;
	}

	public Integer getProductTypes() {
		return ProductTypes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SupermarketProduct [ProductNumber=");
		builder.append(ProductNumber);
		builder.append(", ProductName=");
		builder.append(ProductName);
		builder.append(", ProductTypes=");
		builder.append(ProductTypes);
		builder.append(", NumberOfProducts=");
		builder.append(NumberOfProducts);
		builder.append(", UnitPrice=");
		builder.append(UnitPrice);
		builder.append(", Count=");
		builder.append(Count);
		builder.append("]");
		return builder.toString();
	}

	public void setProductNumber(Integer productNumber) {
		ProductNumber = productNumber;
	}

	public void setProductTypes(Integer productTypes) {
		ProductTypes = productTypes;
	}
}
