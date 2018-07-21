package com.ming.management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.bean.SupermarketVIP;
import com.ming.dao.impl.SupermarketDaoImpl;

/**
 *会员管理菜单
 * 
 * @author SM22
 * @date 2018年7月11日下午5:42:11
 */
public class VIPMenu {


	static String[] name = { "VIPNumber", "VIPName", "ContactInformation", "integral", "Balance" };
	static Scanner sc = new Scanner(System.in);
	private static SupermarketVIP svip = new SupermarketVIP();

	public static void vipMenu() {
		while (true) {
			System.out.println("------------------------------------------------------");
			System.out.println("--------------------欢迎进入会员信息管理-------------------");
			System.out.println("---------------------1.增加会员信息----------------------");
			System.out.println("---------------------2.删除会员信息----------------------");
			System.out.println("---------------------3.查询会员信息----------------------");
			System.out.println("---------------------4.修改会员信息----------------------");
			System.out.println("---------------------5.会员充值-------------------------");
			System.out.println("---------------------6.退出-------------------------");
			System.out.println("请选择菜单");
			String str = sc.next();
			if ("1".equals(str)) {
				new VIPMenu().add();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					vipMenu();
				} else if ("n".equals(str)) {
					break;
				} else {
					System.out.println("输入错误,请重新输入");
				}
			} else if ("2".equals(str)) {
				new VIPMenu().delete();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					vipMenu();
				} else if ("n".equals(str)) {
					break;
				} else {
					System.out.println("输入错误,请重新输入");
				}
			} else if ("3".equals(str)) {
				new VIPMenu().search();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					vipMenu();
				} else if ("n".equals(str)) {
					break;
				} else {
					System.out.println("输入错误,请重新输入");
				}
			} else if ("4".equals(str)) {
				new VIPMenu().update();
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					vipMenu();
				} else if ("n".equals(str)) {
					break;
				} else {
					System.out.println("输入错误,请重新输入");
				}
			}else if ("5".equals(str)) {
				try {
					new VIPMenu().Recharge();
				} catch (SQLException e) {
					e.printStackTrace();
				};
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					vipMenu();
				} else if ("n".equals(str)) {
					break;
				} else {
					System.out.println("输入错误,请重新输入");
				}
			} else if ("6".equals(str)) {
				System.out.println("bye~bye");
				Menu.menu();
				return;
			}
		}

	}

	private void add() {
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------增加会员信息---------------------");
		System.out.println("请输入新的会员类型名称");
		String name = sc.next();
		svip.setVIPName(name);
		System.out.println("请输入新会员的联系方式");
		String tel = sc.next();
		svip.setContactInformation(tel);
		new SupermarketDaoImpl().add(svip);
	}

	private void delete() {
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------删除会员信息---------------------");
		System.out.println("请输入要删除的会员ID");
		Integer ID = sc.nextInt();
		new SupermarketDaoImpl().delete(svip, ID);
	}

	private void search() {
		String FieldName = "";
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------查询会员信息---------------------");
		while (true) {
			System.out.println("请选择要查询的字段名称(输入编号)：\n1.会员编号\t2.会员名称\t3.联系方式");
			String i = sc.next();
			if ("1".equals(i)) {
				FieldName = name[0];
				System.out.println("请输入"+name[0]);
				break;
			} else if ("2".equals(i)) {
				FieldName = name[1];
				System.out.println("请输入"+name[1]);
				break;
			} else if ("3".equals(i)) {
				FieldName = name[2];
				System.out.println("请输入"+name[2]);
				break;
			} else {
				System.out.println("输入错误，请重新输入");
			}
		}
		
		Object Value = (Object) sc.next();
		ResultSet rs = new SupermarketDaoImpl().searchVIP(FieldName, Value);
		try {
			if (!rs.next()) {
				System.out.println("该会员不存在,请重新输入");
				search();
			} else {
				rs.beforeFirst();
				System.out.println("-----------------查询结果-----------------");
				System.out.println("会员编号\t\t" + "会员名称\t\t" + "联系方式\t\t" + "积分\t\t" + "余额");
				while (rs.next()) {
					System.out.println(rs.getInt("VIPNumber") + "\t\t" + rs.getString("VIPName")
					 + "\t\t"
					 + rs.getString("ContactInformation") + "\t" + rs.getDouble("integral") +
					 "\t\t"
					 + rs.getDouble("Balance"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void update() {
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------修改会员信息---------------------");
		String FieldName = null;

		System.out.println("请输入要修改的会员编号");
		Integer old = sc.nextInt();

		while (true) {
			System.out.println("请选择要修改的字段名称(输入编号)：\n1.会员名称\t2.联系方式");
			String i = sc.next();
			  if ("1".equals(i)) {
				FieldName = name[1];
				break;
			} else if ("2".equals(i)) {
				FieldName = name[2];
				break;
			}   else {
				System.out.println("输入错误，请重新输入");
			}
		}
		System.out.println("请输入修改后的值");
		Object newvalue = (Object) sc.next();

		new SupermarketDaoImpl().updateVIP(svip, FieldName, old, newvalue);
	}
	
	/**
	 * 充值
	 * @throws SQLException 
	 */
	private void Recharge() throws SQLException {
		System.out.println("---------------------------------------------------");
		System.out.println("----------------------会员充值-----------------------");
		Double yue = null;
		Integer ID=null;
		while(true) {
			System.out.println("请输入需要充值的ID");
			 ID = sc.nextInt();
			ResultSet rs = new SupermarketDaoImpl().searchVIP("VIPNumber", ID);
			if(!rs.next()) {
				System.out.println("ID输入错误,请重新输入");
			}else {
				rs.first();
				yue = rs.getDouble("Balance");
				System.out.println("当前账户余额为"+rs.getDouble("Balance")+"元");
				break;
			}
		}
		System.out.println("请输入需要充值的金额");
		Double prise = sc.nextDouble();
		yue+=prise;
		new SupermarketDaoImpl().updateVIP(svip, "Balance", ID, yue);
		System.out.println("充值成功，当前余额"+yue+"元");
	}
	
}
