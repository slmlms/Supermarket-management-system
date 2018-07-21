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
 * ���˵�
 */
public class Menu {
	static Scanner sc = new Scanner(System.in);
	public static void menu() {
		System.out.println("-------------------------------------------------");
		System.out.println("��ӭ�����볬�й���ϵͳ");
		System.out.println("-------------------------------------------------");
		System.out.println("��ѡ����˵�");
		System.out.println("------------------1.��Ʒ���͹���--------------------");
		System.out.println("------------------2.��Ʒ����-----------------------");
		System.out.println("------------------3.��Ա����-----------------------");
		System.out.println("------------------4.�������-----------------------");
		System.out.println("------------------5.������ѯ-----------------------");
		System.out.println("------------------6.����ͳ��-----------------------");
		System.out.println("------------------7.�˻�����-------------------");
		System.out.println("------------------8.�˳�--------------------------");
		while(true) {
			System.out.print("������˵���ţ�");
			String str = sc.next();
			if("1".equals(str)) {
				if(AuthenticationAdmin()) {
					TypeMenu.typeMenu();		//��Ʒ���͹���
					break;
				}else {
					System.out.println("�����֤ʧ��");
				}
			}else if("2".equals(str)) {
				if(AuthenticationAdmin()) {
					ProductMenu.productMenu();	//��Ʒ��Ϣ����
					break;
				}else {
					System.out.println("�����֤ʧ��");
				}
			}else if("3".equals(str)) {
				if(AuthenticationAdmin()) {
					VIPMenu.vipMenu();			//��Ա����
					break;
				}else {
					System.out.println("�����֤ʧ��");
				}
			}else if("4".equals(str)) {
				if(AuthenticationCashier()) {
					new PaymentManagement();	//�������
					break;
				}else {
					System.out.println("�����֤ʧ��");
				}
			}else if("5".equals(str)) {
				if(AuthenticationCashier()) {
					new OrderTracking().track();	//������ѯ
					break;
				}else {
					System.out.println("�����֤ʧ��");
				}
			}else if("6".equals(str)) {
				if(AuthenticationCashier()) {
					RankingStatistics.rank();		//����ͳ��
					break;
				}else {
					System.out.println("�����֤ʧ��");
				}
			}
			else if("7".equals(str)) {
				System.out.println("��ѡ��Ҫ�޸ĵ��û�\n1.����Ա\t2.����Ա");
				String str1 = sc.next();
				if ("1".equals(str1)){
					UserManagement.adminManagement();
					break;
				}else if ("2".equals(str1)){
					UserManagement.CashierManagement();
					break;
				}else {
					System.out.println("��֤ʧ��");
					menu();
				}
			}
			else if("8".equals(str)) {
				System.out.println("bye~bye");
				sc.close();					//�˳�
				System.exit(0);
			}else {
				System.out.println("�����������������");
			}
		}
		
	}
	
	/**
	 * ����Ա�����֤
	 */
	private static Boolean AuthenticationAdmin() {
//		boolean bool = false;
			System.out.print("�������û�����");
			String name = sc.next();
			System.out.println("����������");
			String pass = sc.next();
			String key = pass.trim();
			while (key.length()<16) key+="a";
//			if (Administrator.getName()==null || Administrator.getPass()==null){
//				System.out.println("��ע��");
//				UserManagement.adminManagement();
//				return null;
//			}
			if(name.equals(Administrator.getName())&&pass.equals(Administrator.getPass(key).trim())) {
				System.out.println("��¼�ɹ�");
				return true;
			}else {
				return  false;
			}
		
	}
	/**
	 * ����Ա�����֤
	 * @return �Ƿ�ͨ����֤
	 */
	private static Boolean AuthenticationCashier() {
			System.out.println("�������û�����");
			String name = sc.next();
			System.out.println("����������");
			String pass = sc.next();
			String key = pass.trim();
			while (key.length()<16) key+="a";
			if(name.equals( Cashier.getName().trim())&&pass.equals(Cashier.getPass(key).trim())) {
				System.out.println("��¼�ɹ�");
				return  true;
			}else {
				return  false;
			}
		
	}
	
}
