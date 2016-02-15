package com.cheng.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {	
	private static final Logger Log = Logger.getLogger(DBConn.class.getName());
	public static String CONN_URL;
	public static String USERNAME;
	public static String PASSWORD;
	
	private DBConn() {
		
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
		} 
		catch (Exception e) {
			Log.error(e);
		}
		return conn;
	}
}
