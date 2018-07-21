package com.ming.PaymentAndOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.impl.SupermarketDaoImpl;

/**
 * 排行统计 要求按月份及按商品类别统计销量前10的商品及总销量。（收银员权限）
 * 
 * @author SM22
 * @date 2018年7月11日下午2:59:40
 */
public class RankingStatistics {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		rank();
	}

	public static void rank() {
		System.out.println("------------------排行统计------------------");
		while (true) {
			System.out.println("-------------------------------------------------");
			System.out.println("请选择排名方式\n1.按月份统计\t2.按商品类别统计\t3.返回主菜单");
			String count = sc.next();
			if ("1".equals(count)) {
				rankByMonth();			//按月统计
			} else if ("2".equals(count)) {
				rankByType();			//按类别统计
			} else if ("3".equals(count)) {
				Menu.menu();
				return;
			} else {
				System.out.println("输入错误，重新输入");
			}
		}

	}

	private static void rankByMonth() {
		System.out.println("请输入要查询的年月(xxxx-xx)");
		String date = sc.next();
		if(date.matches("\\d{4}(-)\\d{2}")) {
			String sql = "SELECT\n" +
					"Sum(`订单详情`.ProductNum) AS sumnum,\n" +
					"Sum(`订单信息`.AmountOfOrders) AS summoney,\n" +
					"`商品信息`.ProductName,\n" +
					"`商品类型`.TypesName,\n" +
					"`商品类型`.TypesID\n" +
					"FROM\n" +
					"`订单详情`\n" +
					"INNER JOIN `订单信息` ON `订单详情`.OrderID = `订单信息`.OrderID\n" +
					"INNER JOIN `商品信息` ON `订单详情`.ProductID = `商品信息`.ProductNumber\n" +
					"INNER JOIN `商品类型` ON `商品信息`.ProductTypes = `商品类型`.TypesID\n" +
					"WHERE\n" +
					"`订单信息`.OrderTime LIKE '%"+date+"%'\n" +
					"GROUP BY\n" +
					"`商品类型`.TypesName\n" +
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
				
				System.out.println(date+"排名前十的类型为\t\t销量\t\t销售额");
				Double sum = 0.0;
				for (int i = 0; i < nameList.size(); i++) {
					System.out.println("\t"+(i + 1) + "." + nameList.get(i)+"\t\t\t\t\t"+xiaoliangList.get(i)+"\t\t"+numList.get(i));
					sum+=numList.get(i);
				}
				System.out.println(date+"月的总销售额为"+sum+"元");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("日期格式错误,请重新输入");
			rankByMonth();
		}
	}

	/**
	 * 按商品类别统计
	 */
	private static void rankByType() {
		String sql ="SELECT\n" +
				"Sum(`订单详情`.ProductNum) sumnum,\n" +
				"Sum(`订单信息`.AmountOfOrders) summoney,\n" +
				"`商品信息`.ProductName,\n" +
				"`商品类型`.TypesName,\n" +
				"`商品类型`.TypesID\n" +
				"FROM\n" +
				"`订单详情`\n" +
				"INNER JOIN `订单信息` ON `订单详情`.OrderID = `订单信息`.OrderID\n" +
				"INNER JOIN `商品信息` ON `订单详情`.ProductID = `商品信息`.ProductNumber\n" +
				"INNER JOIN `商品类型` ON `商品信息`.ProductTypes = `商品类型`.TypesID\n" +
				"GROUP BY\n" +
				"`商品类型`.TypesName\n" +
				"ORDER BY\n" +
				"Sum(`订单详情`.ProductNum) DESC" ;
			
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
			
			System.out.println("排名前十的类型为\t\t销量\t\t销售额");
			Double sum = 0.0;
			for (int i = 0; i < nameList.size(); i++) {
				System.out.println((i + 1) + "." + nameList.get(i)+"\t\t\t\t"+xiaoliangList.get(i)+"\t\t"+numList.get(i));
				sum+=numList.get(i);
			}
			System.out.println("总销售额为"+sum+"元");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
