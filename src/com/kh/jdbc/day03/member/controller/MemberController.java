package com.kh.jdbc.day03.member.controller;

import java.util.List;

import com.kh.jdbc.day03.member.model.service.MemberService;
import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberController {
	
	private MemberService mService;
	
	public MemberController() {
		mService = new MemberService();  //까먹지말기! ->Null PointException
	}
	/**
	 * 회원 전체 조회
	 * @return List<Member>
	 */
	public List<Member> printAll() {
		List<Member> mList = mService.selectAll();
		return mList;
	}
    /**
     * 회원 아이디로 조회
     * @param memberId
     * @return Member
     */
	public Member printOneById(String memberId) {
		Member member = mService.selectOneById(memberId);
		return member;
	}
	/**
	 * 회원 이름으로 조회
	 * @param memberName
	 * @return List<Member>
	 */
	public List<Member> printAllByName(String memberName) {
		List<Member> mList = mService.selectAllByName(memberName);
		return mList;
	}
	/**
	 * 회원가입
	 * @param member
	 * @return int
	 */
	public int registerMember(Member member) {
		int result = mService.insertMember(member);
		return result;
	}
	/**
	 * 정보수정
	 * @param member
	 * @return int
	 */

	public int modifyMember(Member member) {
		int result = mService.updateMember(member);
		return result;
	}
	
	/**
	 * 회원 탈퇴
	 * @param memberId
	 * @return int
	 */
	public int removeMember(String memberId) {
		int result = mService.deleteMember(memberId);
		return result;
	}

}
