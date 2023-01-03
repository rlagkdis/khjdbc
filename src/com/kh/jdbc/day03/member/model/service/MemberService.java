package com.kh.jdbc.day03.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.common.JDBCTemplate;
import com.kh.jdbc.day03.member.model.dao.MemberDAO;
import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberService {
	
//	private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;
	
	public MemberService() {
//		jdbcTemplate = JDBCTemplate.getDriverLoad();
		mDao = new MemberDAO();  // 생략하지말기!!
	}
	
	public List<Member> selectAll() {
		List<Member> mList = null;
//		MemberDAO mDao = new MemberDAO();
//		JDBCTemplate jdbcTemplate = JDBCTemplate.getDriverLoad();
		Connection conn = JDBCTemplate.getConnection();
		mList = mDao.selectAll(conn);
//		JDBCTemplate.close(conn);
		return mList;
	}
	/**
	 * 회원 아이디로 조회
	 * @param memberId
	 * @return Member
	 */
	public Member selectOneById(String memberId) {
			Connection conn = JDBCTemplate.getConnection();
			Member member = mDao.selectOneById(conn, memberId);
//			JDBCTemplate.close(conn);
			return member;  
		}
	
	/**
	 * 회원 이름으로 조회
	 * @param memberName
	 * @return List<Member>
	 */
	public List<Member> selectAllByName(String memberName) {
			Connection conn = JDBCTemplate.getConnection();
			List<Member> mList = mDao.selectAllByName(conn, memberName);
	//		JDBCTemplate.close(conn);
			return mList;
		}
	/**
	 * 회원가입
	 * @param member
	 * @return int
	 */
	public int insertMember(Member member) {
//		MemberDAO mDao = new MemberDAO();
//		JDBCTemplate jdbcTemplate = JDBCTemplate.getDriverLoad();
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.insertMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
//		jdbcTemplate.close(conn);
		return result;
	}
	/**
	 * 정보 수정
	 * @param member
	 * @return int
	 */
	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.insertMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
//		jdbcTemplate.close(conn);
		return result;
	}
	/**
	 * 회원 탈퇴
	 * @param memberId
	 * @return int
	 */
	public int deleteMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.deleteMember(conn, memberId);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

}
