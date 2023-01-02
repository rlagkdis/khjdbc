package com.kh.jdbc.day02.member.controller;

import java.util.List;

import com.kh.jdbc.day02.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberController {
	/**
	 * 전체 조회
	 * @return List<Member>
	 */
	public List<Member> getMember() {
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectAll();
		return mList;
	}
	/**
	 * 회원가입
	 * @param member
	 * @return int
	 */
	public int registerMember(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.insertMember(member);
		return result;
	}
	
	public Member printOneById(String memberId) {
		MemberDAO mDao = new MemberDAO();
		Member member = mDao.selectOneById(memberId);
		return member;
		// 매개변수에 뭘 넣을지 모르겠으면 쿼리문 생각
		// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? 
		// 반환형은 ResultSet이 1개면 Member
		// ReasultSet 1개 이상이면 List<Member>
	}
	
	public List<Member> printAllByName(String memberName) {
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectAllByName(memberName);
		return mList;
	}
	
	public int removeMember(String memberId) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.deleteMember(memberId);
		return result;
	}
	
	public int modifyMember(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.updateMember(member);
		return result;
	}
	/**
	 * 
	 * @param member
	 * @return
	 */
	public int checkInfo(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.checkLogin(member);
		return result;
	}

}
