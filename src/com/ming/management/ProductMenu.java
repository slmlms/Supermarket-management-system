package com.ming.management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.bean.SupermarketProduct;
import com.ming.dao.impl.SupermarketDaoImpl;

/**
 * 商品管理菜单
 *
 * @author SM22
 * @date 2018年7月11日下午5:42:11
 */
public class ProductMenu {
	public static void main(String[] args) {
		productMenu();
	}

	static String[] name = { "ProductNumber", "ProductName", "ProductTypes", "NumberOfProducts", "UnitPrice", "Count" };
	static Scanner sc = new Scanner(System.in);
	private static SupermarketProduct sp = new SupermarketProduct();

	public static void productMenu() {
		while (true) {
			System.out.println("------------------------------------------------------");
			System.out.println("--------------------欢迎进入商品信息管理-------------------");
			System.out.println("---------------------1.增加商品信息----------------------");
			System.out.println("---------------------2.删除商品信息----------------------");
			System.out.println("---------------------3.查询商品信息----------------------");
			System.out.println("---------------------4.修改商品信息----------------------");
			System.out.println("---------------------5.退出----------------------------");
			System.out.println("请选择菜单");
			String str = sc.next();
			if ("1".equals(str)) {
				new ProductMenu().add();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					productMenu();
				} else if ("n".equals(str)) {
					Menu.menu();
					return;
				} else {
					System.out.println("输入错误,请重新输入");
				}
			} else if ("2".equals(str)) {
				new ProductMenu().delete();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					productMenu();
				} else if ("n".equals(str)) {
					Menu.menu();
					return;
				} else {
					System.out.println("输入错误,请重新输入");
				}
			} else if ("3".equals(str)) {
				new ProductMenu().search();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					productMenu();
				} else if ("n".equals(str)) {
					Menu.menu();
					return;
				} else {
					System.out.println("输入错误,请重新输入");
				}
			} else if ("4".equals(str)) {
				new ProductMenu().update();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					productMenu();
				} else if ("n".equals(str)) {
					Menu.menu();
					return;
				} else {
					System.out.println("输入错误,请重新输入");
				}
			} else if ("5".equals(str)) {
				System.out.println("bye~bye");
				Menu.menu();
				return;
			}
		}

	}

	private void add() {
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------增加商品信息---------------------");
		System.out.println("请输入新的商品类型名称");
		String name = sc.next();
		sp.setProductName(name);
		System.out.println("请输入新商品的类型");
		Integer type = sc.nextInt();
		sp.setProductTypes(type);
		System.out.println("请输入新商品的数量");
		Integer num = sc.nextInt();
		sp.setNumberOfProducts(num);
		System.out.println("请输入新商品的单价");
		Double prise = sc.nextDouble();
		sp.setUnitPrice(prise);
		System.out.println("请输入新商品的折扣");
		Double count = sc.nextDouble();
		sp.setCount(count);
		new SupermarketDaoImpl().add(sp);
	}

	private void delete() {
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------删除商品信息---------------------");
		System.out.println("请输入要删除的商品ID");
		Integer ID = sc.nextInt();
		new SupermarketDaoImpl().delete(sp, ID);
	}



	private void search() {
		String FieldName = null;
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------查询商品信息---------------------");
		while (true) {
			System.out.println("请选择要查询的字段名称(输入编号)：\n1.商品编号\t2.商品名称\t3.商品类型\n4.商品数量\t5.单价\t\t6.折扣");
			String i = sc.next();
			if ("1".equals(i)) {
				FieldName = name[0];
				break;
			} else if ("2".equals(i)) {
				FieldName = name[1];
				break;
			} else if ("3".equals(i)) {
				FieldName = name[2];
				break;
			} else if ("4".equals(i)) {
				FieldName = name[3];
				break;
			} else if ("5".equals(i)) {
				FieldName = name[4];
				break;
			} else if ("6".equals(i)) {
				FieldName = name[5];
				break;
			} else {
				System.out.println("输入错误，请重新输入");
			}
		}
		System.out.println("请输入要查询的值");
		Object Value = (Object) sc.next();
		ResultSet rs = new SupermarketDaoImpl().searchProduct(FieldName, Value);
		try {
			if (!rs.next()) {
				System.out.println("字段值不存在,请重新输入");
				search();
			} else {
				rs.beforeFirst();
				System.out.println("-----------------查询结果-----------------");
				System.out.println("商品编号\t" + "商品名称\t" + "商品类型\t" + "商品数量\t" + "折扣");
				while (rs.next()) {
					System.out.println(rs.getInt("ProductNumber") + "\t\t" + rs.getString("ProductName") + "\t\t"
							+ rs.getInt("ProductTypes") + "\t\t" + rs.getDouble("UnitPrice") + "\t\t"
							+ rs.getDouble("Count"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void update() {
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------修改商品信息---------------------");
		String FieldName = null;

		System.out.println("请输入要修改的商品编号");
		Integer old = sc.nextInt();

		while (true) {
			System.out.println("请选择要修改的字段名称(输入编号)：\n1.商品名称\t2.商品类型\n3.商品数量\t4.单价\t\t5.折扣");
			String i = sc.next();

			if ("1".equals(i)) {
				FieldName = name[1];
				break;
			} else if ("2".equals(i)) {
				FieldName = name[2];
				break;
			} else if ("3".equals(i)) {
				FieldName = name[3];
				break;
			} else if ("4".equals(i)) {
				FieldName = name[4];
				break;
			} else if ("5".equals(i)) {
				FieldName = name[5];
				break;
			} else {
				System.out.println("输入错误，请重新输入");
			}
		}
		System.out.println("请输入修改后的值");
		Object newvalue = (Object) sc.next();

		new SupermarketDaoImpl().updateProuuct(sp, FieldName, old, newvalue);
	}
}
