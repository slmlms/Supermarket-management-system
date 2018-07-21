package com.ming.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ming.dao.bean.SupermarketProduct;
import com.ming.dao.bean.SupermarketTypes;
import com.ming.dao.bean.SupermarketVIP;

public abstract class SupermarketDaoImplDel extends SupermarketDaoImplAdd {

	/**
	 * ɾ����Ʒ��Ϣ
	 */
	@Override
	public void delete(SupermarketProduct sp,Integer ProductNumber) {
		PreparedStatement ps=null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "DELETE FROM `testjdbc`.`��Ʒ��Ϣ` WHERE `ProductNumber` = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ProductNumber);
			ps.execute();
			System.out.println("ɾ���ɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			com.ming.util.Connection.close(con, ps, null);
		}
	}
	/**
	 * ��Ʒ����
	 */
	@Override
	public void delete(SupermarketTypes st,Integer TypesID) {
		PreparedStatement ps=null;
//		Scanner input = new Scanner(System.in);
		Connection con = new com.ming.util.Connection().connection();
		String sql = "DELETE FROM `testjdbc`.`��Ʒ����` WHERE `TypesID` = ?";
		try {
			ps = con.prepareStatement(sql);
//			System.out.println("������Ҫɾ�������ͱ��");
//			Integer i = input.nextInt();
			ps.setInt(1, TypesID);
			ps.execute();
			System.out.println("ɾ���ɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			com.ming.util.Connection.close(con, ps, null);
		}		
	}
	/**
	 * ��Ա��Ϣɾ��
	 */
	@Override
	public void delete(SupermarketVIP svip,Integer VIPNumber) {
		PreparedStatement ps=null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "DELETE FROM `testjdbc`.`��Ա��Ϣ` WHERE `VIPNumber` = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, VIPNumber);
			ps.execute();
			System.out.println("ɾ���ɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			com.ming.util.Connection.close(con, ps, null);		
		
	}

}
}
