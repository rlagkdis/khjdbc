package com.kh.jdbc.day01.student.controller;

import java.util.List;

import com.kh.jdbc.day01.student.model.dao.StudentDao;
import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentController {

	public List<Student> printAll() {
		StudentDao sDao = new StudentDao();
		List<Student> sList = sDao.selectAll();
		return sList;
		//Dao에있는 필드값을 받아서 리턴
	}
	
	public int registerStudent(Student student) {
		StudentDao sDao = new StudentDao();
		int result = sDao.insertMember(student);
		return result;
	}
	
}

