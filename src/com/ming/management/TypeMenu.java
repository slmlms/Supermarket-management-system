package com.ming.management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.bean.SupermarketTypes;
import com.ming.dao.impl.SupermarketDaoImpl;

/**
 * ��Ʒ���͹���˵�
 * @author SM22
 * @date  2018��7��11������5:42:11
 */
public class TypeMenu {

	static String[] name = {"TypesID","TypesName"};
	static Scanner sc = new Scanner(System.in);
	private static SupermarketTypes st = new SupermarketTypes();
	public static void typeMenu() {
		System.out.println("-----------------------------------------------------");
		System.out.println("-------------------��ӭ������Ʒ���͹���-------------------");
		System.out.println("-------------------1.������Ʒ��Ϣ-----------------------");
		System.out.println("-------------------2.ɾ����Ʒ��Ϣ-----------------------");
		System.out.println("-------------------3.��ѯ��Ʒ��Ϣ-----------------------");
		System.out.println("-------------------4.�޸���Ʒ��Ϣ-----------------------");
		System.out.println("-------------------5.�������˵�-------------------------");
		while(true) {
			System.out.println("��ѡ��˵�");
			String str = sc.next();
			if("1".equals(str)) {
				new TypeMenu().add();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if("y".equals(str)) {
					typeMenu();
				}else if("n".equals(str)) {
					Menu.menu();
					return;
				}else {
					System.err.println("�������,����������");
				}
			}else if("2".equals(str)) {
				new TypeMenu().delete();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if("y".equals(str)) {
					typeMenu();
				}else if("n".equals(str)) {
					Menu.menu();
					return;
				}else {
					System.err.println("�������,����������");
				}
			}else if("3".equals(str)) {
				new TypeMenu().search();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if("y".equals(str)) {
					typeMenu();
				}else if("n".equals(str)) {
					Menu.menu();
					return;
				}else {
					System.err.println("�������,����������");
				}
			}else if("4".equals(str)) {
				new TypeMenu().update();
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if("y".equals(str)) {
					typeMenu();
				}else if("n".equals(str)) {
					Menu.menu();
					return;
				}else {
					System.err.println("�������,����������");
				}
			}else if("5".equals(str)) {
				Menu.menu();
				Menu.menu();
				return;
			}else {
				System.err.println("�����������������");
			}
		}
		
	}
	
	
	private void add() {
		System.out.println("***********������Ʒ����***********");
		System.out.println("�������µ���Ʒ��������");
		String name = sc.next();
		st.setTypesName(name);
		new SupermarketDaoImpl().add(st);
	}
	
	private void delete() {
		System.out.println("***********ɾ����Ʒ����***********");
		System.out.println("������Ҫɾ������ƷID");
		Integer ID = sc.nextInt();
		new SupermarketDaoImpl().delete(st, ID);
	}
	
	private void search() {
		String FieldName="";
		System.out.println("***********��ѯ��Ʒ����***********");
		while(true) {
			System.out.println("��ѡ��Ҫ��ѯ���ֶ����ƣ�\n1.��Ʒ����ID\t2.��Ʒ��������");
			String i = sc.next();
			if("1".equals(i)) {
				FieldName = name[0];
				break;
			}else if("2".equals(i)) {
				FieldName = name[1];
				break;
			}else {
				System.out.println("�����������������");
			}
		}
		System.out.println("������Ҫ��ѯ��ֵ");
		Object Value = (Object)sc.next();
		ResultSet rs = new SupermarketDaoImpl().searchTypes(FieldName, Value);
		try {
			if(!rs.next()) {
				System.out.println("�ֶ�ֵ������,����������");
				search();
			}else {
				rs.beforeFirst();
				System.out.println("-----------------��ѯ���-----------------");
				System.out.println("��Ʒ����ID\t" + "��Ʒ��������\t");
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
		System.out.println("***********�޸���Ʒ����***********");
		Integer oldValue=null;
			System.out.println("������Ҫ�޸ĵ�ID");
			oldValue= sc.nextInt();
		System.out.println("�������޸ĺ��ֶε�ֵ:");
		String newValue = sc.next();
		
		new SupermarketDaoImpl().updateTypes(st,  oldValue,  newValue);
	}
}
