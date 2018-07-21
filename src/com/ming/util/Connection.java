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
			//加载配置文件
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Configuration.properties"));
			//加载驱动
			Class.forName(pro.getProperty("driver"));
			//连接数据库
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
	 * 关闭数据库连接
	 * @param con 连接对象
	 * @param ps	预编译对象
	 * @param rs	结果集
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
