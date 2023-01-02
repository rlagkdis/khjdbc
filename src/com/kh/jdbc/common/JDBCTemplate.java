package com.kh.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
	
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "STUDENT";
	private final String PASSWORD = "STUDENT";
	
	private static JDBCTemplate instance;
	
	private JDBCTemplate() {
		try {
			Class.forName(DRIVER_NAME);   // 반드시 해줘야하는 것
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static JDBCTemplate getDriverLoad() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	// MemberDAO 에서 Connection 하던거 따로 빼서 여기서 해주기
	public Connection getConnection() {
		Connection conn = null;   
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false);   // 오토 커밋 해제!!
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
