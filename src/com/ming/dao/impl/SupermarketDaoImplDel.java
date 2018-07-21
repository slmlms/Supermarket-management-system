package com.ming.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ming.dao.bean.SupermarketProduct;
import com.ming.dao.bean.SupermarketTypes;
import com.ming.dao.bean.SupermarketVIP;

public abstract class SupermarketDaoImplDel extends SupermarketDaoImplAdd {

	/**
	 * 删除商品信息
	 */
	@Override
	public void delete(SupermarketProduct sp,Integer ProductNumber) {
		PreparedStatement ps=null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "DELETE FROM `testjdbc`.`商品信息` WHERE `ProductNumber` = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ProductNumber);
			ps.execute();
			System.out.println("删除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			com.ming.util.Connection.close(con, ps, null);
		}
	}
	/**
	 * 商品类型
	 */
	@Override
	public void delete(SupermarketTypes st,Integer TypesID) {
		PreparedStatement ps=null;
//		Scanner input = new Scanner(System.in);
		Connection con = new com.ming.util.Connection().connection();
		String sql = "DELETE FROM `testjdbc`.`商品类型` WHERE `TypesID` = ?";
		try {
			ps = con.prepareStatement(sql);
//			System.out.println("请输入要删除的类型编号");
//			Integer i = input.nextInt();
			ps.setInt(1, TypesID);
			ps.execute();
			System.out.println("删除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			com.ming.util.Connection.close(con, ps, null);
		}		
	}
	/**
	 * 会员信息删除
	 */
	@Override
	public void delete(SupermarketVIP svip,Integer VIPNumber) {
		PreparedStatement ps=null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "DELETE FROM `testjdbc`.`会员信息` WHERE `VIPNumber` = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, VIPNumber);
			ps.execute();
			System.out.println("删除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			com.ming.util.Connection.close(con, ps, null);		
		
	}

}
}
