package com.kh.jdbc.day01.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentDao {
	
	public List<Student> selectAll() {  
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "STUDENT";
		String password = "STUDENT";
		String sql = "SELECT * FROM STUDENT_TBL";
		Student student = null;
		List<Student> sList = null;
		//import java.util.List;
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");  // 오타조심!!!!!!!!!
			//2. DB연결생성
			try {
				Connection conn = DriverManager.getConnection(url,user,password);
				//3. 쿼리문 실행준비(Statement 객체 생성)
				Statement stmt = conn.createStatement();
				//4. 쿼리문 실행 및 결과 받기(SELECT)
				//** SELECT - executeQuery - ResultSet***
				ResultSet rset = stmt.executeQuery(sql);
				sList = new ArrayList<Student>();
				//보내는상자를 담는 택배차
				//stmt.executeQuery(sql);
				// 후처리
				while(rset.next()) {
					student = new Student();
					student.setStudentId(rset.getString("STUDENT_ID"));
					//rset.getString: String으로 가져온다.
					student.setStudentName(rset.getString("STUDENT_NAME"));
					student.setStudentPwd(rset.getString("STUDENT_PWD"));
					student.setAge(rset.getInt("AGE"));
					//컬럼명을 적고 int값을 가져올거면 getInt를 써야한다.
					//AGE 는 int
					student.setEmail(rset.getNString("EMAIL"));
					student.setPhone(rset.getString("PHONE"));
					student.setGender(rset.getString("GENDER"));
					student.setAddress(rset.getString("ADDRESS"));
					student.setHobby(rset.getString("HOBBY"));
					student.setEnrollDate(rset.getDate("ENROLL_DATE"));
					//EnrollDate Date
					sList.add(student);
					//택배차에 택배상자 올리기
					//************잘 까먹음 중요!!잊지말기
				}
				// 6.자원해제
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
		return sList;
		//택배차 보내기 ***
		
	}
	
	public int insertMember(Student student) {
		
		//1. 드라이버 등록
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "STUDENT";
		String password = "STUDENT";
		//String sql = "INSERT INTO STUDENT_TBL VALUES('"+student.getStudentId()+"','"+student.getStudentPwd()+"','"+student.getStudentName()+"','"+student.getGender()+"','"+student.getAge()+"','"+student.getEmail()+"','"+student.getPhone()+"','"+student.getAddress()+"','"+student.getHobby()+"',SYSDATE)";
		String sql = "INSERT INTO STUDENT_TBL VALUES("
				+ "'" + student.getStudentId() +  "',"
				//'khuser02' -> "'" + khuser02 + "'," 
				+ "'"+student.getStudentPwd()+"',"
				+ "'"+student.getStudentName()+"',"
				+ "'"+student.getGender()+"',"
				+     student.getAge()+","
				//int값은 '' 없이 그냥 33 이렇게 썼었으니까
				//, 만 있으면됨 그래서 33 + "," -> student.getAge()+"," 이것만!
				+ "'"+student.getEmail()+"',"
				+ "'"+student.getPhone()+"',"
				+ "'"+student.getAddress()+"',"
				+ "'"+student.getHobby()+"',"
				+ "SYSDATE)";
		//String sql = "INSERT INTO STUDENT_TBL VALUES('khuser03','pass03','삼용자','M',33,'khuser03@naver.com','01088320393','서울시 동대문구','자전거,축구',SYSDATE)";
		//원래 'khuser03','pass03',,이걸 '"+student.getStudentId()+"' 이렇게바꿈
		int result = 0;
		//밑에서 쓰면 리턴할때 사라지니까 밖에서 선언
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				Connection conn = DriverManager.getConnection(url,user,password);
				Statement stmt = conn.createStatement();
				//쿼리문 실행 - DML (INSERT , UPDATE, DELETE)
				result = stmt.executeUpdate(sql);
				//executeUpdate :성공,실패에따라 리턴값이달라짐
				//성공하면 0이아닌행의갯수, 실패하면 0
				//리턴값: 0또는0이 아닌 숫자라서 
				//그래서 리턴타입이 int ******
				//**DML- executeUpdate- int**
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
