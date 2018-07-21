package com.ming.PaymentAndOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.impl.SupermarketDaoImpl;

/**
 * ����ͳ�� Ҫ���·ݼ�����Ʒ���ͳ������ǰ10����Ʒ����������������ԱȨ�ޣ�
 * 
 * @author SM22
 * @date 2018��7��11������2:59:40
 */
public class RankingStatistics {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		rank();
	}

	public static void rank() {
		System.out.println("------------------����ͳ��------------------");
		while (true) {
			System.out.println("-------------------------------------------------");
			System.out.println("��ѡ��������ʽ\n1.���·�ͳ��\t2.����Ʒ���ͳ��\t3.�������˵�");
			String count = sc.next();
			if ("1".equals(count)) {
				rankByMonth();			//����ͳ��
			} else if ("2".equals(count)) {
				rankByType();			//�����ͳ��
			} else if ("3".equals(count)) {
				Menu.menu();
				return;
			} else {
				System.out.println("���������������");
			}
		}

	}

	private static void rankByMonth() {
		System.out.println("������Ҫ��ѯ������(xxxx-xx)");
		String date = sc.next();
		if(date.matches("\\d{4}(-)\\d{2}")) {
			String sql = "SELECT\n" +
					"Sum(`��������`.ProductNum) AS sumnum,\n" +
					"Sum(`������Ϣ`.AmountOfOrders) AS summoney,\n" +
					"`��Ʒ��Ϣ`.ProductName,\n" +
					"`��Ʒ����`.TypesName,\n" +
					"`��Ʒ����`.TypesID\n" +
					"FROM\n" +
					"`��������`\n" +
					"INNER JOIN `������Ϣ` ON `��������`.OrderID = `������Ϣ`.OrderID\n" +
					"INNER JOIN `��Ʒ��Ϣ` ON `��������`.ProductID = `��Ʒ��Ϣ`.ProductNumber\n" +
					"INNER JOIN `��Ʒ����` ON `��Ʒ��Ϣ`.ProductTypes = `��Ʒ����`.TypesID\n" +
					"WHERE\n" +
					"`������Ϣ`.OrderTime LIKE '%"+date+"%'\n" +
					"GROUP BY\n" +
					"`��Ʒ����`.TypesName\n" +
					"ORDER BY\n" +
					"sumnum DESC";
			
			ResultSet rs = new SupermarketDaoImpl().search(sql);
			List<String> nameList = new ArrayList<>();
			List<Double> numList = new ArrayList<>();
			List<Integer> xiaoliangList = new ArrayList<>();
			try {
				while (rs.next()) {
					nameList.add(rs.getString("TypesName"));
					numList.add(rs.getDouble("summoney"));
					xiaoliangList.add(rs.getInt("sumnum"));
				}
				
				System.out.println(date+"����ǰʮ������Ϊ\t\t����\t\t���۶�");
				Double sum = 0.0;
				for (int i = 0; i < nameList.size(); i++) {
					System.out.println("\t"+(i + 1) + "." + nameList.get(i)+"\t\t\t\t\t"+xiaoliangList.get(i)+"\t\t"+numList.get(i));
					sum+=numList.get(i);
				}
				System.out.println(date+"�µ������۶�Ϊ"+sum+"Ԫ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("���ڸ�ʽ����,����������");
			rankByMonth();
		}
	}

	/**
	 * ����Ʒ���ͳ��
	 */
	private static void rankByType() {
		String sql ="SELECT\n" +
				"Sum(`��������`.ProductNum) sumnum,\n" +
				"Sum(`������Ϣ`.AmountOfOrders) summoney,\n" +
				"`��Ʒ��Ϣ`.ProductName,\n" +
				"`��Ʒ����`.TypesName,\n" +
				"`��Ʒ����`.TypesID\n" +
				"FROM\n" +
				"`��������`\n" +
				"INNER JOIN `������Ϣ` ON `��������`.OrderID = `������Ϣ`.OrderID\n" +
				"INNER JOIN `��Ʒ��Ϣ` ON `��������`.ProductID = `��Ʒ��Ϣ`.ProductNumber\n" +
				"INNER JOIN `��Ʒ����` ON `��Ʒ��Ϣ`.ProductTypes = `��Ʒ����`.TypesID\n" +
				"GROUP BY\n" +
				"`��Ʒ����`.TypesName\n" +
				"ORDER BY\n" +
				"Sum(`��������`.ProductNum) DESC" ;
			
		ResultSet rs = new SupermarketDaoImpl().search(sql);
		List<String> nameList = new ArrayList<>();
		List<Double> numList = new ArrayList<>();
		List<Integer> xiaoliangList = new ArrayList<>();
		try {
			while (rs.next()) {
				nameList.add(rs.getString("TypesName"));
				numList.add(rs.getDouble("summoney"));
				xiaoliangList.add(rs.getInt("sumnum"));
			}
			
			System.out.println("����ǰʮ������Ϊ\t\t����\t\t���۶�");
			Double sum = 0.0;
			for (int i = 0; i < nameList.size(); i++) {
				System.out.println((i + 1) + "." + nameList.get(i)+"\t\t\t\t"+xiaoliangList.get(i)+"\t\t"+numList.get(i));
				sum+=numList.get(i);
			}
			System.out.println("�����۶�Ϊ"+sum+"Ԫ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
