package com.ming.dao.bean;

public class SupermarketVIP {

	/**
	 * ��Ա��Ϣ
	 */
	private Integer VIPNumber; // ��Ա���
	private String VIPName; // ��Ա����
	private String ContactInformation; // ��ϵ��ʽ
	private Double integral; // ��Ա����
	private Double Balance; // ���

	public String getVIPName() {
		return VIPName;
	}

	public void setVIPName(String vIPName) {
		VIPName = vIPName;
	}

	public String getContactInformation() {
		return ContactInformation;
	}

	public void setContactInformation(String contactInformation) {
		ContactInformation = contactInformation;
	}

	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public Double getBalance() {
		return Balance;
	}

	public void setBalance(Double balance) {
		Balance = balance;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SupermarketVIP [VIPNumber=");
		builder.append(VIPNumber);
		builder.append(", VIPName=");
		builder.append(VIPName);
		builder.append(", ContactInformation=");
		builder.append(ContactInformation);
		builder.append(", integral=");
		builder.append(integral);
		builder.append(", Balance=");
		builder.append(Balance);
		builder.append("]");
		return builder.toString();
	}

	public Integer getVIPNumber() {
		return VIPNumber;
	}

	public void setVIPNumber(Integer vIPNumber) {
		VIPNumber = vIPNumber;
	}

}
