package com.ming.role;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * 收银员
 * 
 * @author SM22
 * @date 2018年7月11日下午4:59:13
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
	 * 用户名验证
	 * @return	用户名是否正确
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
	 * 密码验证
	 * @return	密码是否正确
	 */
	public static String getPass(String key) {
		try {
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("cashier.properties"));
			//解码
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
			System.out.println("请输入新的用户名");
			String str = sc.next();
//			pro.setProperty("CashierPss",pass1);	//保存之前的密码
			pro.setProperty("CashierName", str);          //保存用户名
//			pro.store(fos, "cashier.properties");         //使用输出流保存到磁盘
			System.out.println("修改成功，新的用户名为：" + str);
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
			System.out.println("请输入新的密码");
			String pass = sc.next();
			key = pass.trim();          //用密码本身作为秘钥
			while (key.length() < 16) key += "a";       //秘钥长度不足16时用a填充
			pass = UserManagement.Byte2HexStr(UserManagement.aesEncrypt(pass,key));         //使用aes进行加密,并转换成16进制字符串保存
			pro.setProperty("CashierPss", pass);          //保存密码
//			pro.setProperty("CashierName",pro.getProperty("CashierName"));		//保存用户名，防止重新写入是丢失
			pro.store(fos, "cashier.properties");         //使用输出流保存到磁盘
			System.out.println("密码修改成功");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
