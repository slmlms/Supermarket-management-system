package com.ming.dao.bean;

public class SupermarketVIP {

	/**
	 * 会员信息
	 */
	private Integer VIPNumber; // 会员编号
	private String VIPName; // 会员名称
	private String ContactInformation; // 联系方式
	private Double integral; // 会员积分
	private Double Balance; // 余额

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
