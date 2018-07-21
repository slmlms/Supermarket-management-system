package com.ming.dao.bean;

public class SupermarketTypes {
	/**
	 * 商品类型
	 */
	private Integer TypesID; // 商品类型ID
	private String TypesName; // 商品类型名称
	public Integer getTypesID() {
		return TypesID;
	}
	public void setTypesID(Integer typesID) {
		TypesID = typesID;
	}
	public String getTypesName() {
		return TypesName;
	}
	public void setTypesName(String typesName) {
		TypesName = typesName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SupermarketTypes [TypesID=");
		builder.append(TypesID);
		builder.append(", TypesName=");
		builder.append(TypesName);
		builder.append("]");
		return builder.toString();
	}
}
