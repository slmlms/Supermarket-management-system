package com.ming.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ming.dao.SupermarketDAO;
import com.ming.dao.bean.OrderDetails;
import com.ming.dao.bean.OrderInformation;
import com.ming.dao.bean.SupermarketProduct;
import com.ming.dao.bean.SupermarketTypes;
import com.ming.dao.bean.SupermarketVIP;

public abstract class SupermarketDaoImplAdd implements SupermarketDAO {
	/**
	 * ������Ʒ��Ϣ
	 */
	@Override
	public void add(SupermarketProduct sp) {
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "insert into testjdbc.��Ʒ��Ϣ (ProductName,NumberOfProducts,UnitPrice,Count) " + "values (?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, sp.getProductName());
			ps.setInt(2, sp.getNumberOfProducts());
			ps.setDouble(3, sp.getUnitPrice());
			ps.setDouble(4, sp.getCount());
			ps.executeUpdate();
			System.out.println("���ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.ming.util.Connection.close(con, ps, null);
		}

	}

	/**
	 * ������Ʒ����
	 */
	@Override
	public void add(SupermarketTypes st) {
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "insert into testjdbc.��Ʒ���� (TypesName) " + "values (?)";
		try {
			ps = con.prepareStatement(sql);
//			ps.setInt(1, st.getTypesID());
			ps.setString(1, st.getTypesName());
			ps.executeUpdate();
			System.out.println("���ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.ming.util.Connection.close(con, ps, null);
		}

	}

	/**
	 * ���ӻ�Ա��Ϣ
	 */
	@Override
	public void add(SupermarketVIP svip) {
		PreparedStatement ps = null;
		Connection con = new com.ming.util.Connection().connection();
		String sql = "insert into testjdbc.��Ա��Ϣ (VIPName,ContactInformation) " + "values (?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, svip.getVIPName());
			ps.setString(2, svip.getContactInformation());
			
			ps.executeUpdate();
			System.out.println("���ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.ming.util.Connection.close(con, ps, null);
		}

	}
	
	
	
	@Override
	public void add(OrderInformation oi) {
		Connection con = new com.ming.util.Connection().connection();
		PreparedStatement ps =null;
		String sql = "INSERT INTO `testjdbc`.`������Ϣ`(`OrderID`,`UserID`, `AmountOfOrders`, `OrderTime`, `PaymentTypes`) "
				+ "VALUES (?,?,?,?,?)";
		try {
			ps= con.prepareStatement(sql);
			ps.setObject(1, oi.getOrderID());
			ps.setObject(2, oi.getUserID());
			ps.setObject(3, oi.getAmountOfOrders());
			ps.setObject(4, oi.getOrderTime());		//��������ʱ��
			ps.setObject(5, oi.getPaymentTypes());
			ps.executeUpdate();
			System.out.println("���������ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			com.ming.util.Connection.close(con, ps, null);
		}
	}
	/**
	 * ������������
	 */
	@Override
	public void add(OrderDetails od) {
		Connection con = new com.ming.util.Connection().connection();
		PreparedStatement ps =null;
		String sql = "INSERT INTO `testjdbc`.`��������`(`OrderID`,`ProductID`, `ProductNum`, `CommodityPrice`) "
				+ "VALUES (?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setObject(1, od.getOrderID());
			ps.setObject(2, od.getProductID());
			ps.setObject(3, od.getProductNum());
			ps.setObject(4, od.getCommodityPrice());
			ps.executeUpdate();
			System.out.println("�������鴴���ɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			com.ming.util.Connection.close(con, ps, null);
		}
	}

}
