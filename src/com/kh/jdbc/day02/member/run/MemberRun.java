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
			case 2 : break;
			case 3 : break;
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
			case 5 : break;
			case 6 : break;
			default : break;
			}
		}
	}

}
