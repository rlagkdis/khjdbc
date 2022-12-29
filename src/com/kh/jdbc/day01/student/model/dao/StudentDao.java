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
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "STUDENT";
	private final String PASSWORD = "STUDENT";
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	//보이면안되니까 private / 바뀌면안되니까 final 이니까 대문자!!(URL)
	
	public List<Student> selectAll() {  
		String sql = "SELECT * FROM STUDENT_TBL";
		Student student = null;
		List<Student> sList = null;
		//import java.util.List;
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);  // 오타조심!!!!!!!!!
			//2. DB연결생성
			try {
				Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
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
	
	public List<Student> selectAllByName(String studentName) {
		List<Student> sList = null;
		Student student = null;
		String sql = "SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = '"+studentName+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			sList = new ArrayList<Student>();
			while(rset.next()) {
				student = new Student();
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setAge(rset.getInt("AGE"));
				student.setEmail(rset.getNString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				student.setGender(rset.getString("GENDER"));
				student.setAddress(rset.getString("ADDRESS"));
				student.setHobby(rset.getString("HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
				sList.add(student);
			}
			rset.close();
			stmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList;
	}
	
	
	public Student selectOneById(String studentId) {
		Student student = null;
		String sql = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = '"+studentId+"'";
		try {
			Class.forName(DRIVER_NAME);
			// DB 연결 객체 생성
			Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			// Statement 생성, 쿼리문 실행 준비 완료
			Statement stmt = conn.createStatement();
			// 쿼리문 실행, 결과 받기
			ResultSet rset = stmt.executeQuery(sql);
			//후처리, rset을 그대로 못써서
			if(rset.next()) {
				student = new Student();
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setAge(rset.getInt("AGE"));
				student.setEmail(rset.getNString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				student.setGender(rset.getString("GENDER"));
				student.setAddress(rset.getString("ADDRESS"));
				student.setHobby(rset.getString("HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			//6. 자원해제
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
		
	}

	public int insertMember(Student student) {
		
		//1. 드라이버 등록
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
			Class.forName(DRIVER_NAME);
			try {
				Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
				Statement stmt = conn.createStatement();
				//쿼리문 실행 - DML (INSERT , UPDATE, DELETE)
				result = stmt.executeUpdate(sql);
				//executeUpdate :성공,실패에따라 리턴값이달라짐
				//성공하면 0이아닌행의갯수, 실패하면 0
				//리턴값: 0또는0이 아닌 숫자라서 
				//그래서 리턴타입이 int ******
				//**DML- executeUpdate- int**
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
		return result;
	}
	
	public int updateStudent(Student student) {
		int result = 0;
		String sql = "UPDATE STUDENT_TBL SET "
				+ "STUDENT_PWD = '"+student.getStudentPwd()+"', "
						+ "EMAIL = '"+student.getEmail()+"', "
								+ "PHONE = '"+student.getPhone()+"', "
										+ "ADDRESS = '"+student.getAddress()+"', "
												+ "HOBBY = '"+student.getHobby()+"' "
														+ "WHERE STUDENT_ID = '"+student.getStudentId()+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);

			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteMember(String studentId) {
		int result = 0;
		String sql = "DELETE FROM STUDENT_TBL WHERE STUDENT_ID = '"+studentId+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
