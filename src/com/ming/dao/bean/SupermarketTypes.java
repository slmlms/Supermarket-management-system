package com.ming.dao.bean;

public class SupermarketTypes {
	/**
	 * ��Ʒ����
	 */
	private Integer TypesID; // ��Ʒ����ID
	private String TypesName; // ��Ʒ��������
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
