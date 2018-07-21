package com.ming.management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.bean.SupermarketProduct;
import com.ming.dao.impl.SupermarketDaoImpl;

/**
 * ��Ʒ����˵�
 *
 * @author SM22
 * @date 2018��7��11������5:42:11
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
			System.out.println("--------------------��ӭ������Ʒ��Ϣ����-------------------");
			System.out.println("---------------------1.������Ʒ��Ϣ----------------------");
			System.out.println("---------------------2.ɾ����Ʒ��Ϣ----------------------");
			System.out.println("---------------------3.��ѯ��Ʒ��Ϣ----------------------");
			System.out.println("---------------------4.�޸���Ʒ��Ϣ----------------------");
			System.out.println("---------------------5.�˳�----------------------------");
			System.out.println("��ѡ��˵�");
			String str = sc.next();
			if ("1".equals(str)) {
				new ProductMenu().add();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					productMenu();
				} else if ("n".equals(str)) {
					Menu.menu();
					return;
				} else {
					System.out.println("�������,����������");
				}
			} else if ("2".equals(str)) {
				new ProductMenu().delete();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					productMenu();
				} else if ("n".equals(str)) {
					Menu.menu();
					return;
				} else {
					System.out.println("�������,����������");
				}
			} else if ("3".equals(str)) {
				new ProductMenu().search();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					productMenu();
				} else if ("n".equals(str)) {
					Menu.menu();
					return;
				} else {
					System.out.println("�������,����������");
				}
			} else if ("4".equals(str)) {
				new ProductMenu().update();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if ("y".equals(str)) {
					productMenu();
				} else if ("n".equals(str)) {
					Menu.menu();
					return;
				} else {
					System.out.println("�������,����������");
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
		System.out.println("---------------------������Ʒ��Ϣ---------------------");
		System.out.println("�������µ���Ʒ��������");
		String name = sc.next();
		sp.setProductName(name);
		System.out.println("����������Ʒ������");
		Integer type = sc.nextInt();
		sp.setProductTypes(type);
		System.out.println("����������Ʒ������");
		Integer num = sc.nextInt();
		sp.setNumberOfProducts(num);
		System.out.println("����������Ʒ�ĵ���");
		Double prise = sc.nextDouble();
		sp.setUnitPrice(prise);
		System.out.println("����������Ʒ���ۿ�");
		Double count = sc.nextDouble();
		sp.setCount(count);
		new SupermarketDaoImpl().add(sp);
	}

	private void delete() {
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------ɾ����Ʒ��Ϣ---------------------");
		System.out.println("������Ҫɾ������ƷID");
		Integer ID = sc.nextInt();
		new SupermarketDaoImpl().delete(sp, ID);
	}



	private void search() {
		String FieldName = null;
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------��ѯ��Ʒ��Ϣ---------------------");
		while (true) {
			System.out.println("��ѡ��Ҫ��ѯ���ֶ�����(������)��\n1.��Ʒ���\t2.��Ʒ����\t3.��Ʒ����\n4.��Ʒ����\t5.����\t\t6.�ۿ�");
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
				System.out.println("�����������������");
			}
		}
		System.out.println("������Ҫ��ѯ��ֵ");
		Object Value = (Object) sc.next();
		ResultSet rs = new SupermarketDaoImpl().searchProduct(FieldName, Value);
		try {
			if (!rs.next()) {
				System.out.println("�ֶ�ֵ������,����������");
				search();
			} else {
				rs.beforeFirst();
				System.out.println("-----------------��ѯ���-----------------");
				System.out.println("��Ʒ���\t" + "��Ʒ����\t" + "��Ʒ����\t" + "��Ʒ����\t" + "�ۿ�");
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
		System.out.println("---------------------�޸���Ʒ��Ϣ---------------------");
		String FieldName = null;

		System.out.println("������Ҫ�޸ĵ���Ʒ���");
		Integer old = sc.nextInt();

		while (true) {
			System.out.println("��ѡ��Ҫ�޸ĵ��ֶ�����(������)��\n1.��Ʒ����\t2.��Ʒ����\n3.��Ʒ����\t4.����\t\t5.�ۿ�");
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
				System.out.println("�����������������");
			}
		}
		System.out.println("�������޸ĺ��ֵ");
		Object newvalue = (Object) sc.next();

		new SupermarketDaoImpl().updateProuuct(sp, FieldName, old, newvalue);
	}
}
