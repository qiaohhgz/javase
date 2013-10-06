package com.jim.demo.access.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccessHelper {
	private static final String AccessDataSourceUrl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=myAccessDB.mdb";// 打开数据库源
	private static final String ExcelDataSourceUrl = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=myWorkbookDB.xls";// 打开数据库源

	private static final String AccessDriverClassName = "sun.jdbc.odbc.JdbcOdbcDriver";

	private static Connection conn;

	static public Connection getConnection(String url) {
		try {
			Class.forName(AccessDriverClassName);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	static public void readAccessData() throws Exception {
        getConnection(AccessDataSourceUrl);

		PreparedStatement ps1 = conn.prepareStatement("insert into [User](name,password,createOn) values(?,?,?)");
		ps1.setObject(1, "jim");
		ps1.setObject(2, "123456");
		ps1.setObject(3, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		int executeUpdate = ps1.executeUpdate();
		System.out.println(executeUpdate);

		// sheet = 表
		// 第一行 = 列名 类型不能使用默认的
		PreparedStatement ps2 = conn.prepareStatement("select * from [User]");
		ResultSet rs = ps2.executeQuery();
		while (rs.next()) {
			System.out.println(MessageFormat.format("Name:{0} Password:{1} createOn:{2}", rs.getString("Name"),
					rs.getObject("Password"), rs.getObject("createOn")));
		}
		closeConnection(conn);
	}

	static public void readExcelData() throws Exception {
        getConnection(ExcelDataSourceUrl);

		// sheet = 表
		// 第一行 = 列名 类型不能使用默认的
		PreparedStatement ps2 = conn.prepareStatement("select Name,Password from [myWorkbookDB$]");
		ResultSet rs = ps2.executeQuery();
		while (rs.next()) {
			System.out.println(MessageFormat.format("Name:{0} Password:{1} ", rs.getString("Name"),
					rs.getObject("Password")));
		}
		closeConnection(conn);
	}

	static private void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			readAccessData();
			// readExcelData();
		} catch (Exception e) {
			closeConnection(conn);
			e.printStackTrace();
		}

	}
}
