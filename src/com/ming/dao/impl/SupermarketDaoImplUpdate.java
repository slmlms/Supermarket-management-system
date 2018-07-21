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
	 * �޸���Ʒ��Ϣ
	 */
	@Override
	public void update(String sql) {
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();

		try {
			ps = con.prepareStatement(sql);
			ps.execute();
			System.out.println("�޸ĳɹ�");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.ming.util.Connection.close(con, ps, null);
		}
	}

	/**
	 *
	 * @param sp	��Ʒ����
	 * @param FieldName	Ҫ�޸ĵ��ֶ�
	 * @param oldValue	��ƷID
	 * @param newValue	Ҫ�޸ĵ��ֶ�ֵ
	 */
	public void updateProuuct(SupermarketProduct sp, String FieldName, Integer oldValue, Object newValue) {
		String sql = "UPDATE `testjdbc`.`��Ʒ��Ϣ` SET " + FieldName + " = '" + newValue + "' WHERE `ProductNumber` = "
				+ oldValue + "";
		update(sql);
		show(sp, oldValue);
	}

	/**
	 * �޸���Ʒ����
	 * 
	 * @param st
	 *            ��Ʒ���Ͷ���
	 * @param FieldName
	 *            �޸��ֶε�����
	 * @param Value
	 *            �޸��ֶε�ֵ
	 * @param FieldName1
	 *            ��ѯ�ֶε�����
	 * @param Value1
	 *            ��ѯ�ֶε�ֵ
	 */

	public void updateTypes(SupermarketTypes st, Integer oldValue, String newValue) {
		String sql = "UPDATE `testjdbc`.`��Ʒ����` SET `TypesName` = '" + newValue + "' WHERE `TypesID` = " + oldValue + "";
		update(sql);
		show(st, oldValue);

	}

	/**
	 * �޸Ļ�Ա��Ϣ
	 * 
	 * @param svip
	 *            ��Ա����
	 * @param FieldName
	 *            Ҫ�޸ĵ��ֶ�
	 * @param Value
	 *            �޸ĺ��ֵ
	 * @param FieldName1
	 *            ��ѯ�������ֶ�
	 * @param Value1
	 *            ��ѯ������ֵ
	 */

	public void updateVIP(SupermarketVIP svip, String FieldName, Integer oldValue, Object newValue) {

		String sql = "UPDATE `testjdbc`.`��Ա��Ϣ` SET `"+FieldName+"` = '"+newValue+"' WHERE `VIPNumber` = "+oldValue+"";
		update(sql);
		show(svip, oldValue);

	}

	/**
	 * ��ѯ�޸ĺ����Ʒ��Ϣ
	 * 
	 * @param sp
	 *            ��Ʒ��Ϣ
	 * @param id
	 *            ��Ʒ���
	 * @return �����
	 */
	private void show(SupermarketProduct sp,  Object FieldValue) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "SELECT * FROM `testjdbc`.`��Ʒ��Ϣ` WHERE `ProductNumber` =" + FieldValue + "  ";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("��Ʒ���\t" + "��Ʒ����\t" + "��Ʒ����\t" + "����\t" + "�ۿ�");
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
	 * ��ѯ�޸ĺ����Ʒ����
	 * 
	 * @param sp
	 *            ��Ʒ��Ϣ
	 * @param id
	 *            ��Ʒ���
	 * @return �����
	 */
	private void show(SupermarketTypes st, Object FieldValue) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "SELECT * FROM `testjdbc`.`��Ʒ����` WHERE `TypesID` =" + FieldValue + "  ";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("����ID\t" + "��������\t");
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
	 * ��ѯ�޸ĺ�Ļ�Ա��Ϣ
	 * 
	 * @param sp
	 *            ��Ա��Ϣ
	 * @param id
	 *            ��Ա���
	 * @return �����
	 */
	private  void show(SupermarketVIP svip, Object FieldValue) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "SELECT * FROM `testjdbc`.`��Ա��Ϣ` WHERE `VIPNumber` =" + FieldValue + "  ";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("��Ա����\t" + "��Ա����\t" + "��ϵ��ʽ\t" + "����\t" + "���");
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
