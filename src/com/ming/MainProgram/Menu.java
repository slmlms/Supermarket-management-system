package com.ming.MainProgram;

import java.util.Scanner;

import com.ming.PaymentAndOrder.OrderTracking;
import com.ming.PaymentAndOrder.PaymentManagement;
import com.ming.PaymentAndOrder.RankingStatistics;
import com.ming.management.ProductMenu;
import com.ming.management.TypeMenu;
import com.ming.management.VIPMenu;
import com.ming.role.Administrator;
import com.ming.role.Cashier;
import com.ming.role.UserManagement;


/**
 * 主菜单
 */
public class Menu {
	static Scanner sc = new Scanner(System.in);
	public static void menu() {
		System.out.println("-------------------------------------------------");
		System.out.println("欢迎进♂入超市管理系统");
		System.out.println("-------------------------------------------------");
		System.out.println("请选♂择菜单");
		System.out.println("------------------1.商品类型管理--------------------");
		System.out.println("------------------2.商品管理-----------------------");
		System.out.println("------------------3.会员管理-----------------------");
		System.out.println("------------------4.购买管理-----------------------");
		System.out.println("------------------5.订单查询-----------------------");
		System.out.println("------------------6.排行统计-----------------------");
		System.out.println("------------------7.账户管理-------------------");
		System.out.println("------------------8.退出--------------------------");
		while(true) {
			System.out.print("请输入菜单编号：");
			String str = sc.next();
			if("1".equals(str)) {
				if(AuthenticationAdmin()) {
					TypeMenu.typeMenu();		//商品类型管理
					break;
				}else {
					System.out.println("身份验证失败");
				}
			}else if("2".equals(str)) {
				if(AuthenticationAdmin()) {
					ProductMenu.productMenu();	//商品信息管理
					break;
				}else {
					System.out.println("身份验证失败");
				}
			}else if("3".equals(str)) {
				if(AuthenticationAdmin()) {
					VIPMenu.vipMenu();			//会员管理
					break;
				}else {
					System.out.println("身份验证失败");
				}
			}else if("4".equals(str)) {
				if(AuthenticationCashier()) {
					new PaymentManagement();	//购买管理
					break;
				}else {
					System.out.println("身份验证失败");
				}
			}else if("5".equals(str)) {
				if(AuthenticationCashier()) {
					new OrderTracking().track();	//订单查询
					break;
				}else {
					System.out.println("身份验证失败");
				}
			}else if("6".equals(str)) {
				if(AuthenticationCashier()) {
					RankingStatistics.rank();		//排行统计
					break;
				}else {
					System.out.println("身份验证失败");
				}
			}
			else if("7".equals(str)) {
				System.out.println("请选择要修改的用户\n1.管理员\t2.收银员");
				String str1 = sc.next();
				if ("1".equals(str1)){
					UserManagement.adminManagement();
					break;
				}else if ("2".equals(str1)){
					UserManagement.CashierManagement();
					break;
				}else {
					System.out.println("验证失败");
					menu();
				}
			}
			else if("8".equals(str)) {
				System.out.println("bye~bye");
				sc.close();					//退出
				System.exit(0);
			}else {
				System.out.println("输入错误，请重新输入");
			}
		}
		
	}
	
	/**
	 * 管理员身份验证
	 */
	private static Boolean AuthenticationAdmin() {
//		boolean bool = false;
			System.out.print("请输入用户名：");
			String name = sc.next();
			System.out.println("请输入密码");
			String pass = sc.next();
			String key = pass.trim();
			while (key.length()<16) key+="a";
//			if (Administrator.getName()==null || Administrator.getPass()==null){
//				System.out.println("请注册");
//				UserManagement.adminManagement();
//				return null;
//			}
			if(name.equals(Administrator.getName())&&pass.equals(Administrator.getPass(key).trim())) {
				System.out.println("登录成功");
				return true;
			}else {
				return  false;
			}
		
	}
	/**
	 * 收银员身份验证
	 * @return 是否通过验证
	 */
	private static Boolean AuthenticationCashier() {
			System.out.println("请输入用户名：");
			String name = sc.next();
			System.out.println("请输入密码");
			String pass = sc.next();
			String key = pass.trim();
			while (key.length()<16) key+="a";
			if(name.equals( Cashier.getName().trim())&&pass.equals(Cashier.getPass(key).trim())) {
				System.out.println("登录成功");
				return  true;
			}else {
				return  false;
			}
		
	}
	
}
