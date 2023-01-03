package com.kh.jdbc.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
	
	private static Properties prop; // 보안을 위해서 사용
//	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USER = "STUDENT";
//	private static final String PASSWORD = "STUDENT";  //static이라 글자 기울어짐!
	
	private static Connection conn;
	
	private JDBCTemplate() {}
	
	public static Connection getConnection() { // static메소드안에있는건 static변수여야됨
			try {
				prop = new Properties();
				FileReader reader = new FileReader("resources/dev.properties");
				prop.load(reader);  // 파일을읽어서 prop에 옮겨놓음
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				if(conn == null || conn.isClosed()) {
					Class.forName(prop.getProperty("driver"));  //dev.properties 파일에서 = 앞에있는 키값 써주기
				conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);   // 오토 커밋 해제!!
			} 
			}catch (Exception e) {  // 부모 Exception으로 예외처리 다한거
				e.printStackTrace();
			}
			return conn;
		}
	
	// 커밋
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 롤백
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 연결 해제
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
