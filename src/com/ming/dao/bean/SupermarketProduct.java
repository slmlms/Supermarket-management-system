package com.ming.dao.bean;


public class SupermarketProduct {
	/**
	 * ��Ʒ��Ϣ
	 */
	private Integer ProductNumber; // ��Ʒ���
	private String ProductName; // ��Ʒ����
	private Integer ProductTypes; // ��Ʒ����ID
	private Integer NumberOfProducts;// ��Ʒ����
	private Double UnitPrice; // ����
	private Double Count; // �ۿ�

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
