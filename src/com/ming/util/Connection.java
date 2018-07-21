package com.ming.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Connection {

	public java.sql.Connection  connection() {
		java.sql.Connection conn =null;
		try {
			Properties pro = new Properties();
			//���������ļ�
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Configuration.properties"));
			//��������
			Class.forName(pro.getProperty("driver"));
			//�������ݿ�
			 conn = DriverManager.getConnection(pro.getProperty("url"), 
					pro.getProperty("uname"), pro.getProperty("pass"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
	 * �ر����ݿ�����
	 * @param con ���Ӷ���
	 * @param ps	Ԥ�������
	 * @param rs	�����
	 */
	public static void close(java.sql.Connection con,PreparedStatement ps,ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(java.sql.Connection con,PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
