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
	 * 根据商品名查询商品信息
	 */
	public ResultSet searchProduct(String FieldName, Object Value) {
		String sql = "SELECT * FROM `testjdbc`.`商品信息` WHERE `" + FieldName + "` = '" + Value + "' LIMIT 0, 10";
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
			rs.beforeFirst(); // 显示商品信息后光标已经在最后一行，如果不将光标移至开头，会出现数据无法提取的现象
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 查询商品类型
	 */
	public ResultSet searchTypes(String FieldName, Object Value) {
		ResultSet rs = null;
		String sql = "SELECT * FROM `testjdbc`.`商品类型` WHERE `"+FieldName+"` LIKE '%"+Value+"%' LIMIT 0, 1000";
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
	 * 查询会员信息
	 */
	public ResultSet searchVIP(String FieldName, Object Value) {
		ResultSet rs = null;
		String sql = "SELECT * FROM `testjdbc`.`会员信息` WHERE `"+FieldName+"` = '"+Value+"' LIMIT 0, 100";
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
	 * 查询订单信息
	 * 
	 * @param FieldName	商品编号或是会员编号
	 * @param Value
	 * @return
	 */
	public ResultSet searchOrderInformation(String FieldName, Object Value) {
		ResultSet rs = null;
		String sql = "SELECT * FROM `testjdbc`.`订单信息` WHERE `" + FieldName + "` = '" + Value + "' LIMIT 0, 10";
		rs = search(sql);
		return rs;
	}

	/**
	 * 查询订单详情
	 * 
	 * @param FieldName	
	 * @param Value	商品编号或是会员编号
	 * @return
	 */
	public ResultSet searchOrderDetails(String FieldName, Object Value) {
		ResultSet rs = null;
		String sql = "SELECT * FROM `testjdbc`.`订单详情` WHERE `" + FieldName + "` = '" + Value + "' LIMIT 0, 10";
		rs = search(sql);
		return rs;
	}

}
