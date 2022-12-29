package com.kh.jdbc.day02.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberView {
	
	public int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 회원관리 프로그램 === ===");
		System.out.println("1. 회원 전체 조회");
		System.out.println("2. 회원 아이디로 조회");
		System.out.println("3. 회원 이름으로 조회");
		System.out.println("4. 회원 가입");
		System.out.println("5. 회원 정보 수정");
		System.out.println("6. 회원 탈퇴");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice;
	}
	
	public void showAll(List<Member> mList) {
		for (Member mOne : mList) {
			System.out.print("ID : " + mOne.getMemberId());
			System.out.print(", 비밀번호 : " + mOne.getMemberPwd());
			System.out.print(", 이름 : " + mOne.getMemberName());
			System.out.print(", 성별 : " + mOne.getMemberGender());
			System.out.print(", 나이 : " + mOne.getMemberAge());
			System.out.print(", 이메일 : " + mOne.getMemberEmail());
			System.out.print(", 번호 : " + mOne.getMemberPhone());
			System.out.print(", 주소 : " + mOne.getMemberAddress());
			System.out.print(", 취미 : " + mOne.getMemberHobby());
			System.out.println(", 가입날짜 : " + mOne.getMemberDate());
			// 마지막애는 ln 안하면 === 회원관리 프로그램 === 이게 옆에 붙어서 나온다
		}
	}
	
	public void showOne(Member member) {
		System.out.print("ID : " + member.getMemberId());
		System.out.print(", 비밀번호 : " + member.getMemberPwd());
		System.out.print(", 이름 : " + member.getMemberName());
		System.out.print(", 성별 : " + member.getMemberGender());
		System.out.print(", 나이 : " + member.getMemberAge());
		System.out.print(", 이메일 : " + member.getMemberEmail());
		System.out.print(", 번호 : " + member.getMemberPhone());
		System.out.print(", 주소 : " + member.getMemberAddress());
		System.out.print(", 취미 : " + member.getMemberHobby());
		System.out.println(", 가입날짜 : " + member.getMemberDate());
	}
	
	public Member modifyMember(Member member) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 비밀번호 입력 : ");
		String memberPwd = sc.next();
		System.out.println("수정할 이메일 입력 : ");
		String memberEmail = sc.next();
		System.out.println("수정할 번호 입력 : ");
		String memberPhone = sc.next();
		System.out.println("수정할 주소 입력 : ");
		String memberAddress = sc.next();
		System.out.println("수정할 취미 입력 : ");
		String memberHobby = sc.next();
		member.setMemberPwd(memberPwd);
		member.setMemberEmail(memberEmail);
		member.setMemberPhone(memberPhone);
		member.setMemberAddress(memberAddress);
		member.setMemberHobby(memberHobby);
		return member;
	}
	
	public Member inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 입력 : ");
		String memberPwd = sc.next();
		System.out.print("이름 입력 : ");
		String memberName = sc.next();
		System.out.print("성별 입력 : ");
		String memberGender = sc.next();
		System.out.print("나이 입력 : ");
		int memberAge = sc.nextInt();
		System.out.print("이메일 입력 : ");
		String memberEmail = sc.next();
		System.out.print("번호 입력 : ");
		String memberPhone = sc.next();
		System.out.print("주소 입력 : ");
		sc.nextLine();
		String memberAddress = sc.nextLine();
		System.out.print("취미 입력 : ");
		String memberHobby = sc.next();
		Member member = new Member(memberId,memberPwd,memberName,memberGender,memberAge,
				memberEmail,memberPhone,memberAddress,memberHobby,null);
		return member;
	}
	
	public String inputMemberName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 입력 : ");       // 가이드메시지 잊지말기
		String memberName = sc.next();
		return memberName;
	}
	
	public String inputMemberId(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.println(message + "할 아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}
	
	public void displaySuccess(String message) {
		System.out.println("[서비스 성공]" + message);
	}
	
	public void displayError(String message) {
		System.out.println("[서비스 실패]" + message);
	}

}
