package com.ming.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ming.dao.bean.SupermarketProduct;
import com.ming.dao.bean.SupermarketTypes;
import com.ming.dao.bean.SupermarketVIP;

public class SupermarketDaoImplUpdate extends SupermarketDaoImplSearch {
	/**
	 * 修改商品信息
	 */
	@Override
	public void update(String sql) {
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();

		try {
			ps = con.prepareStatement(sql);
			ps.execute();
			System.out.println("修改成功");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.ming.util.Connection.close(con, ps, null);
		}
	}

	/**
	 *
	 * @param sp	商品对象
	 * @param FieldName	要修改的字段
	 * @param oldValue	商品ID
	 * @param newValue	要修改的字段值
	 */
	public void updateProuuct(SupermarketProduct sp, String FieldName, Integer oldValue, Object newValue) {
		String sql = "UPDATE `testjdbc`.`商品信息` SET " + FieldName + " = '" + newValue + "' WHERE `ProductNumber` = "
				+ oldValue + "";
		update(sql);
		show(sp, oldValue);
	}

	/**
	 * 修改商品类型
	 * 
	 * @param st
	 *            商品类型对象
	 * @param FieldName
	 *            修改字段的名称
	 * @param Value
	 *            修改字段的值
	 * @param FieldName1
	 *            查询字段的名称
	 * @param Value1
	 *            查询字段的值
	 */

	public void updateTypes(SupermarketTypes st, Integer oldValue, String newValue) {
		String sql = "UPDATE `testjdbc`.`商品类型` SET `TypesName` = '" + newValue + "' WHERE `TypesID` = " + oldValue + "";
		update(sql);
		show(st, oldValue);

	}

	/**
	 * 修改会员信息
	 * 
	 * @param svip
	 *            会员对象
	 * @param FieldName
	 *            要修改的字段
	 * @param Value
	 *            修改后的值
	 * @param FieldName1
	 *            查询条件的字段
	 * @param Value1
	 *            查询条件的值
	 */

	public void updateVIP(SupermarketVIP svip, String FieldName, Integer oldValue, Object newValue) {

		String sql = "UPDATE `testjdbc`.`会员信息` SET `"+FieldName+"` = '"+newValue+"' WHERE `VIPNumber` = "+oldValue+"";
		update(sql);
		show(svip, oldValue);

	}

	/**
	 * 查询修改后的商品信息
	 * 
	 * @param sp
	 *            商品信息
	 * @param id
	 *            商品编号
	 * @return 结果集
	 */
	private void show(SupermarketProduct sp,  Object FieldValue) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "SELECT * FROM `testjdbc`.`商品信息` WHERE `ProductNumber` =" + FieldValue + "  ";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("商品编号\t" + "商品名称\t" + "商品类型\t" + "单价\t" + "折扣");
			while (rs.next()) {
				System.out.println(rs.getInt("ProductNumber") + "\t\t" + rs.getString("ProductName") + "\t\t"
						+ rs.getInt("ProductTypes") + "\t\t" + rs.getDouble("UnitPrice") + "\t\t"
						+ rs.getDouble("Count"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.ming.util.Connection.close(con, ps, rs);
		}
	}

	/**
	 * 查询修改后的商品类型
	 * 
	 * @param sp
	 *            商品信息
	 * @param id
	 *            商品编号
	 * @return 结果集
	 */
	private void show(SupermarketTypes st, Object FieldValue) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "SELECT * FROM `testjdbc`.`商品类型` WHERE `TypesID` =" + FieldValue + "  ";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("类型ID\t" + "类型名称\t");
			while (rs.next()) {
				System.out.println(rs.getInt("TypesID") + "\t\t" + rs.getString("TypesName") + "\t\t");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.ming.util.Connection.close(con, ps, rs);
		}
	}

	/**
	 * 查询修改后的会员信息
	 * 
	 * @param sp
	 *            会员信息
	 * @param id
	 *            会员编号
	 * @return 结果集
	 */
	private  void show(SupermarketVIP svip, Object FieldValue) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "SELECT * FROM `testjdbc`.`会员信息` WHERE `VIPNumber` =" + FieldValue + "  ";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("会员卡号\t" + "会员名称\t" + "联系方式\t" + "积分\t" + "余额");
			while (rs.next()) {
				System.out.println(rs.getInt("VIPNumber") + "\t\t" + rs.getString("VIPName") + "\t\t"
						+ rs.getString("ContactInformation") + "\t" + rs.getDouble("integral") + "\t\t"
						+ rs.getDouble("Balance"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.ming.util.Connection.close(con, ps, rs);
		}
	}

}
