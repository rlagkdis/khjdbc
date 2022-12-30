package com.kh.jdbc.day02.member.run;

import java.util.List;

import com.kh.jdbc.day02.member.controller.MemberController;
import com.kh.jdbc.day02.member.model.vo.Member;
import com.kh.jdbc.day02.member.view.MemberView;

public class MemberRun {
	public static void main(String [] args) {
		
		MemberView mView = new MemberView();
		MemberController mCon = new MemberController();
		Member member = null;
		String memberId = "";
		String memberName = "";
		int result = 0;
		
		goodbye :
			
		while(true) {
			int choice = mView.mainMenu();
			switch(choice) {
			case 0 : break goodbye;
			
			case 1 : 
				// 전체 조회
				List<Member> mList = mCon.getMember();
				mView.showAll(mList);
				break;
				
			case 2 : 
				// 아이디로 조회
				memberId = mView.inputMemberId("조회");
				member = mCon.printOneById(memberId);
				if(member != null) {
					mView.showOne(member);
				} else {
					mView.displayError("일치하는 정보 없음");
				}
				break;
				
			case 3 : 
				// 이름으로 조회
				memberName = mView.inputMemberName();
				mList = mCon.printAllByName(memberName);
				if(!mList.isEmpty()) {
					mView.showAll(mList);
				} else {
					mView.displayError("일치하는 정보 없음");
				}
				break;
				
			case 4 : 
				// 회원가입
				member = mView.inputMember();
				result = mCon.registerMember(member);
				if(result > 0) {
					mView.displaySuccess("가입 성공");
				} else {
					mView.displayError("가입 실패");
				}
				break;
				
			case 5 : 
				// 회원 정보 수정
				memberId = mView.inputMemberId("수정");
				member = mCon.printOneById(memberId);
				if(member != null) {
					member = mView.modifyMember(member);
					mCon.modifyMember(member);
					mView.displaySuccess("수정 완료");
				} else {
					mView.displayError("수정 실패");
				}
				break;
				
			case 6 : 
				// 회원탈퇴
				memberId = mView.inputMemberId("탈퇴");
				result = mCon.removeMember(memberId);
				if(result != 0) {
					mView.displaySuccess("탈퇴 성공");
				} else {
					mView.displayError("탈퇴 실패");
				}
				break;
			case 7 : 
				// 로그인
				member = mView.inputLoginInfo();
				result = mCon.checkInfo(member);
				if(result > 0) {
					// 로그인 성공
					mView.displaySuccess("로그인 성공");
				} else {
					//로그인 실패
					mView.displayError("일치하는 정보가 존재하지 않습니다.");
				}
				break;
			default : break;
			}
		}
	}

}
