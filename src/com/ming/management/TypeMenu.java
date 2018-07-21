package com.ming.management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.bean.SupermarketTypes;
import com.ming.dao.impl.SupermarketDaoImpl;

/**
 * 商品类型管理菜单
 * @author SM22
 * @date  2018年7月11日下午5:42:11
 */
public class TypeMenu {

	static String[] name = {"TypesID","TypesName"};
	static Scanner sc = new Scanner(System.in);
	private static SupermarketTypes st = new SupermarketTypes();
	public static void typeMenu() {
		System.out.println("-----------------------------------------------------");
		System.out.println("-------------------欢迎进入商品类型管理-------------------");
		System.out.println("-------------------1.增加商品信息-----------------------");
		System.out.println("-------------------2.删除商品信息-----------------------");
		System.out.println("-------------------3.查询商品信息-----------------------");
		System.out.println("-------------------4.修改商品信息-----------------------");
		System.out.println("-------------------5.返回主菜单-------------------------");
		while(true) {
			System.out.println("请选择菜单");
			String str = sc.next();
			if("1".equals(str)) {
				new TypeMenu().add();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if("y".equals(str)) {
					typeMenu();
				}else if("n".equals(str)) {
					Menu.menu();
					return;
				}else {
					System.err.println("输入错误,请重新输入");
				}
			}else if("2".equals(str)) {
				new TypeMenu().delete();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if("y".equals(str)) {
					typeMenu();
				}else if("n".equals(str)) {
					Menu.menu();
					return;
				}else {
					System.err.println("输入错误,请重新输入");
				}
			}else if("3".equals(str)) {
				new TypeMenu().search();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if("y".equals(str)) {
					typeMenu();
				}else if("n".equals(str)) {
					Menu.menu();
					return;
				}else {
					System.err.println("输入错误,请重新输入");
				}
			}else if("4".equals(str)) {
				new TypeMenu().update();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if("y".equals(str)) {
					typeMenu();
				}else if("n".equals(str)) {
					Menu.menu();
					return;
				}else {
					System.err.println("输入错误,请重新输入");
				}
			}else if("5".equals(str)) {
				Menu.menu();
				Menu.menu();
				return;
			}else {
				System.err.println("输入错误，请重新输入");
			}
		}
		
	}
	
	
	private void add() {
		System.out.println("***********增加商品类型***********");
		System.out.println("请输入新的商品类型名称");
		String name = sc.next();
		st.setTypesName(name);
		new SupermarketDaoImpl().add(st);
	}
	
	private void delete() {
		System.out.println("***********删除商品类型***********");
		System.out.println("请输入要删除的商品ID");
		Integer ID = sc.nextInt();
		new SupermarketDaoImpl().delete(st, ID);
	}
	
	private void search() {
		String FieldName="";
		System.out.println("***********查询商品类型***********");
		while(true) {
			System.out.println("请选择要查询的字段名称：\n1.商品类型ID\t2.商品类型名称");
			String i = sc.next();
			if("1".equals(i)) {
				FieldName = name[0];
				break;
			}else if("2".equals(i)) {
				FieldName = name[1];
				break;
			}else {
				System.out.println("输入错误，请重新输入");
			}
		}
		System.out.println("请输入要查询的值");
		Object Value = (Object)sc.next();
		ResultSet rs = new SupermarketDaoImpl().searchTypes(FieldName, Value);
		try {
			if(!rs.next()) {
				System.out.println("字段值不存在,请重新输入");
				search();
			}else {
				rs.beforeFirst();
				System.out.println("-----------------查询结果-----------------");
				System.out.println("商品类型ID\t" + "商品类型名称\t");
				while(rs.next()) {
					 System.out.println(rs.getInt("TypesID") + "\t\t" + rs.getString("TypesName")
					 + "\t\t");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void update() {
		System.out.println("***********修改商品类型***********");
		Integer oldValue=null;
			System.out.println("请输入要修改的ID");
			oldValue= sc.nextInt();
		System.out.println("请输入修改后字段的值:");
		String newValue = sc.next();
		
		new SupermarketDaoImpl().updateTypes(st,  oldValue,  newValue);
	}
}
