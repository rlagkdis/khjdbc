package com.kh.jdbc.day00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {

	public static void main(String[] args) {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성(연결된 상태)
		 * 3. Statement 객체 생성(쿼리문 실행준비)
		 * 4. SQL 전송 (쿼리문 실행된 상태)
		 * 5. 결과값 받기 (ResultSet을 받은 상태) -> 후처리
		 * 6. 자원해제 (close())
		 */
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; //안바뀌는값 오타나기쉬움!
			String user = "KH";
			String password = "KH";
			String sql = "SELECT * FROM EMPLOYEE";
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); //안바뀌는값 오타나기쉬움
			//Class.forName : ojdbc6.jar를 사용하려고쓰는거
			//2. DB 연결 생성
			try {
				Connection conn = DriverManager.getConnection(url,user,password);
				// new 없이 DriverManager클래스적고 바로 .getConnection 메소드호출할수있는건
				//.getConnection 이 메소드가 static메소드라서
				//3. 쿼리문 실행준비(Statement 객체 생성)
				Statement stmt = conn.createStatement();
				//4. 쿼리문 실행
				ResultSet rset = stmt.executeQuery(sql);
				// 후처리 - 디비에서 가져온 데이터 사용
				while(rset.next()) {
					//rset.next()호출-> 다음값이있으면 true값을 반환
					System.out.println("사원 아이디 : "+ rset.getString("EMP_ID"));
					//rset.getString : 필드값 rset.getString(컬럼명쓰기)
					//컬럼명작성해서 필드값을 받아와서출력
				}
				
				// 자원해제
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
