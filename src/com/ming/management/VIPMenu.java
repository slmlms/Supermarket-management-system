package com.ming.management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.bean.SupermarketVIP;
import com.ming.dao.impl.SupermarketDaoImpl;

/**
 *��Ա����˵�
 * 
 * @author SM22
 * @date 2018��7��11������5:42:11
 */
public class VIPMenu {


	static String[] name = { "VIPNumber", "VIPName", "ContactInformation", "integral", "Balance" };
	static Scanner sc = new Scanner(System.in);
	private static SupermarketVIP svip = new SupermarketVIP();

	public static void vipMenu() {
		while (true) {
			System.out.println("------------------------------------------------------");
			System.out.println("--------------------��ӭ�����Ա��Ϣ����-------------------");
			System.out.println("---------------------1.���ӻ�Ա��Ϣ----------------------");
			System.out.println("---------------------2.ɾ����Ա��Ϣ----------------------");
			System.out.println("---------------------3.��ѯ��Ա��Ϣ----------------------");
			System.out.println("---------------------4.�޸Ļ�Ա��Ϣ----------------------");
			System.out.println("---------------------5.��Ա��ֵ-------------------------");
			System.out.println("---------------------6.�˳�-------------------------");
			System.out.println("��ѡ��˵�");
			String str = sc.next();
			if ("1".equals(str)) {
				new VIPMenu().add();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					vipMenu();
				} else if ("n".equals(str)) {
					break;
				} else {
					System.out.println("�������,����������");
				}
			} else if ("2".equals(str)) {
				new VIPMenu().delete();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					vipMenu();
				} else if ("n".equals(str)) {
					break;
				} else {
					System.out.println("�������,����������");
				}
			} else if ("3".equals(str)) {
				new VIPMenu().search();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					vipMenu();
				} else if ("n".equals(str)) {
					break;
				} else {
					System.out.println("�������,����������");
				}
			} else if ("4".equals(str)) {
				new VIPMenu().update();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					vipMenu();
				} else if ("n".equals(str)) {
					break;
				} else {
					System.out.println("�������,����������");
				}
			}else if ("5".equals(str)) {
				try {
					new VIPMenu().Recharge();
				} catch (SQLException e) {
					e.printStackTrace();
				};
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					vipMenu();
				} else if ("n".equals(str)) {
					break;
				} else {
					System.out.println("�������,����������");
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
		System.out.println("---------------------���ӻ�Ա��Ϣ---------------------");
		System.out.println("�������µĻ�Ա��������");
		String name = sc.next();
		svip.setVIPName(name);
		System.out.println("�������»�Ա����ϵ��ʽ");
		String tel = sc.next();
		svip.setContactInformation(tel);
		new SupermarketDaoImpl().add(svip);
	}

	private void delete() {
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------ɾ����Ա��Ϣ---------------------");
		System.out.println("������Ҫɾ���Ļ�ԱID");
		Integer ID = sc.nextInt();
		new SupermarketDaoImpl().delete(svip, ID);
	}

	private void search() {
		String FieldName = "";
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------��ѯ��Ա��Ϣ---------------------");
		while (true) {
			System.out.println("��ѡ��Ҫ��ѯ���ֶ�����(������)��\n1.��Ա���\t2.��Ա����\t3.��ϵ��ʽ");
			String i = sc.next();
			if ("1".equals(i)) {
				FieldName = name[0];
				System.out.println("������"+name[0]);
				break;
			} else if ("2".equals(i)) {
				FieldName = name[1];
				System.out.println("������"+name[1]);
				break;
			} else if ("3".equals(i)) {
				FieldName = name[2];
				System.out.println("������"+name[2]);
				break;
			} else {
				System.out.println("�����������������");
			}
		}
		
		Object Value = (Object) sc.next();
		ResultSet rs = new SupermarketDaoImpl().searchVIP(FieldName, Value);
		try {
			if (!rs.next()) {
				System.out.println("�û�Ա������,����������");
				search();
			} else {
				rs.beforeFirst();
				System.out.println("-----------------��ѯ���-----------------");
				System.out.println("��Ա���\t\t" + "��Ա����\t\t" + "��ϵ��ʽ\t\t" + "����\t\t" + "���");
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
		System.out.println("---------------------�޸Ļ�Ա��Ϣ---------------------");
		String FieldName = null;

		System.out.println("������Ҫ�޸ĵĻ�Ա���");
		Integer old = sc.nextInt();

		while (true) {
			System.out.println("��ѡ��Ҫ�޸ĵ��ֶ�����(������)��\n1.��Ա����\t2.��ϵ��ʽ");
			String i = sc.next();
			  if ("1".equals(i)) {
				FieldName = name[1];
				break;
			} else if ("2".equals(i)) {
				FieldName = name[2];
				break;
			}   else {
				System.out.println("�����������������");
			}
		}
		System.out.println("�������޸ĺ��ֵ");
		Object newvalue = (Object) sc.next();

		new SupermarketDaoImpl().updateVIP(svip, FieldName, old, newvalue);
	}
	
	/**
	 * ��ֵ
	 * @throws SQLException 
	 */
	private void Recharge() throws SQLException {
		System.out.println("---------------------------------------------------");
		System.out.println("----------------------��Ա��ֵ-----------------------");
		Double yue = null;
		Integer ID=null;
		while(true) {
			System.out.println("��������Ҫ��ֵ��ID");
			 ID = sc.nextInt();
			ResultSet rs = new SupermarketDaoImpl().searchVIP("VIPNumber", ID);
			if(!rs.next()) {
				System.out.println("ID�������,����������");
			}else {
				rs.first();
				yue = rs.getDouble("Balance");
				System.out.println("��ǰ�˻����Ϊ"+rs.getDouble("Balance")+"Ԫ");
				break;
			}
		}
		System.out.println("��������Ҫ��ֵ�Ľ��");
		Double prise = sc.nextDouble();
		yue+=prise;
		new SupermarketDaoImpl().updateVIP(svip, "Balance", ID, yue);
		System.out.println("��ֵ�ɹ�����ǰ���"+yue+"Ԫ");
	}
	
}
