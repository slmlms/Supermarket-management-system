package com.ming.role;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * ����Ա
 * 
 * @author SM22
 * @date 2018��7��11������4:59:13
 */
public class Cashier {
	

	private static String name;
	private static String pass;

	private static Properties pro = new Properties();
	private static final String filepath = "src/cashier.properties";
	private static Scanner sc = new Scanner(System.in);
	private static OutputStream fos;

	static {
		try {
			fos = new FileOutputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �û�����֤
	 * @return	�û����Ƿ���ȷ
	 */
	public static String getName() {
		try {
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("cashier.properties"));
			name = pro.getProperty("CashierName");
			return name;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}


	/**
	 * ������֤
	 * @return	�����Ƿ���ȷ
	 */
	public static String getPass(String key) {
		try {
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("cashier.properties"));
			//����
			pass = UserManagement.aesDecrypt(UserManagement.HexStr2Byte(pro.getProperty("CashierPss")),key);
			return pass;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pass;
	}

	public static void setName() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filepath);
			pro.load(fis);
//			String pass1 = pro.getProperty("CashierPss");
			fis.close();
			System.out.println("�������µ��û���");
			String str = sc.next();
//			pro.setProperty("CashierPss",pass1);	//����֮ǰ������
			pro.setProperty("CashierName", str);          //�����û���
//			pro.store(fos, "cashier.properties");         //ʹ����������浽����
			System.out.println("�޸ĳɹ����µ��û���Ϊ��" + str);
//            Administrator.name = str;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setPass() {
		FileInputStream fis=null;
		String key = null;
		try {
			fis = new FileInputStream(filepath);
			pro.load(fis);
//			String name1 =pro.getProperty("CashierName");
			fis.close();
			System.out.println("�������µ�����");
			String pass = sc.next();
			key = pass.trim();          //�����뱾����Ϊ��Կ
			while (key.length() < 16) key += "a";       //��Կ���Ȳ���16ʱ��a���
			pass = UserManagement.Byte2HexStr(UserManagement.aesEncrypt(pass,key));         //ʹ��aes���м���,��ת����16�����ַ�������
			pro.setProperty("CashierPss", pass);          //��������
//			pro.setProperty("CashierName",pro.getProperty("CashierName"));		//�����û�������ֹ����д���Ƕ�ʧ
			pro.store(fos, "cashier.properties");         //ʹ����������浽����
			System.out.println("�����޸ĳɹ�");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
