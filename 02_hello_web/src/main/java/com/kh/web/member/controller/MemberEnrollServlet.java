package com.kh.web.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.member.model.service.MemberService;
import com.kh.web.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 * Servlet은 Controller 역할을 담당함
 */
@WebServlet("/member/enroll.do")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberEnrollServlet doPost");
	////////////////////////////////////////////////////////////////////////////	
		// 0 . 인코딩 처리
		// 1. 요청처리: 사용자 입력값을 자바 변수에 옮겨 담기
		// 2. 업무로직: DB에 insert요청
		// 3. 응답 html 처리: jsp에 위임
		// 		사용자입력값 확인 및 등록 성공 메시지 통보 및, 성공 시 index 페이지로 이동
	////////////////////////////////////////////////////////////////////////////	

		// 1.
//		Member member = new Member(userId, pwd, userName, ssn, email, tel, job, hobby, null);
		Member member = null;
		
		// 2.
		int result = memberService.insertMember(member);
		
	}

}
