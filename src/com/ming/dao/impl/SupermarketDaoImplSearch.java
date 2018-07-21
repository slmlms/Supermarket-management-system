package com.ming.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SupermarketDaoImplSearch extends SupermarketDaoImplDel {

	@Override
	public ResultSet search(String sql) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = new com.ming.util.Connection().connection();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * ������Ʒ����ѯ��Ʒ��Ϣ
	 */
	public ResultSet searchProduct(String FieldName, Object Value) {
		String sql = "SELECT * FROM `testjdbc`.`��Ʒ��Ϣ` WHERE `" + FieldName + "` = '" + Value + "' LIMIT 0, 10";
		ResultSet rs = null;
		try {
			rs = search(sql);
			// System.out.println("ProductNumber\t" + "ProductName\t" + "ProductTypes\t" +
			// "UnitPrice\t" + "Count");
			// while (rs.next()) {
			// System.out.println(rs.getInt("ProductNumber") + "\t\t" +
			// rs.getString("ProductName") + "\t\t"
			// + rs.getInt("ProductTypes") + "\t\t" + rs.getDouble("UnitPrice") + "\t\t"
			// + rs.getDouble("Count"));
			// }
			rs.beforeFirst(); // ��ʾ��Ʒ��Ϣ�����Ѿ������һ�У�����������������ͷ������������޷���ȡ������
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * ��ѯ��Ʒ����
	 */
	public ResultSet searchTypes(String FieldName, Object Value) {
		ResultSet rs = null;
		String sql = "SELECT * FROM `testjdbc`.`��Ʒ����` WHERE `"+FieldName+"` LIKE '%"+Value+"%' LIMIT 0, 1000";
		rs = search(sql);
		// try {
		// System.out.println("TypesID\t" + "TypesName\t");
		// while (rs.next()) {
//		 System.out.println(rs.getInt("TypesID") + "\t\t" + rs.getString("TypesName")
//		 + "\t\t");
		// }
		try {
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		return rs;
	}

	/**
	 * ��ѯ��Ա��Ϣ
	 */
	public ResultSet searchVIP(String FieldName, Object Value) {
		ResultSet rs = null;
		String sql = "SELECT * FROM `testjdbc`.`��Ա��Ϣ` WHERE `"+FieldName+"` = '"+Value+"' LIMIT 0, 100";
		rs = search(sql);
		// try {
		// System.out.println("VIPNumber\t" + "VIPName\t" + "ContactInformation\t" +
		// "integral\t" + "Balance");
		// while (rs.next()) {
		// System.out.println(rs.getInt("VIPNumber") + "\t\t" + rs.getString("VIPName")
		// + "\t\t"
		// + rs.getString("ContactInformation") + "\t" + rs.getDouble("integral") +
		// "\t\t"
		// + rs.getDouble("Balance"));
		// }
		try {
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		return rs;
	}

	/**
	 * ��ѯ������Ϣ
	 * 
	 * @param FieldName	��Ʒ��Ż��ǻ�Ա���
	 * @param Value
	 * @return
	 */
	public ResultSet searchOrderInformation(String FieldName, Object Value) {
		ResultSet rs = null;
		String sql = "SELECT * FROM `testjdbc`.`������Ϣ` WHERE `" + FieldName + "` = '" + Value + "' LIMIT 0, 10";
		rs = search(sql);
		return rs;
	}

	/**
	 * ��ѯ��������
	 * 
	 * @param FieldName	
	 * @param Value	��Ʒ��Ż��ǻ�Ա���
	 * @return
	 */
	public ResultSet searchOrderDetails(String FieldName, Object Value) {
		ResultSet rs = null;
		String sql = "SELECT * FROM `testjdbc`.`��������` WHERE `" + FieldName + "` = '" + Value + "' LIMIT 0, 10";
		rs = search(sql);
		return rs;
	}

}
