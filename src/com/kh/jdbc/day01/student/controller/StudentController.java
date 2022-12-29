package com.kh.jdbc.day01.student.controller;

import java.util.List;

import com.kh.jdbc.day01.student.model.dao.StudentDao;
import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentController {
	/**
	 * 학생 전체 목록 조회
	 * @return List<Student>
	 */
	public List<Student> printAll() {
		StudentDao sDao = new StudentDao();
		List<Student> sList = sDao.selectAll();
		return sList;
		//Dao에있는 필드값을 받아서 리턴
	}
	/**
	 * 학생 이름으로 조회
	 * @param studentName
	 * @return Lsit<Student>
	 */
	public List<Student> printAllByName(String studentName) {
		StudentDao sDao = new StudentDao();
		List<Student> sList = sDao.selectAllByName(studentName);
		return sList;
	}
	/**
	 * 학생 아이디로 조회
	 * @param studentId
	 * @return Student
	 */
	public Student printOneById(String studentId) {
		StudentDao sDao = new StudentDao();
		Student student = sDao.selectOneById(studentId);
		return student;
		
	}
	/**
	 * 회원 가입
	 * @param student
	 * @return result
	 */

	public int registerStudent(Student student) {
		StudentDao sDao = new StudentDao();
		int result = sDao.insertMember(student);
		return result;
	}
	/**
	 * 회원 탈퇴
	 * @param studentId
	 */
	public int removeStudent(String studentId) { //왜 int냐? - 리턴값이int니까
		StudentDao sDao = new StudentDao();
		int result = sDao.deleteMember(studentId);
		return result;
	}
	
	public int modifyStudent(Student student) {
		StudentDao sDao = new StudentDao();
		int result = sDao.updateStudent(student);
		return result;
	}
	
}

