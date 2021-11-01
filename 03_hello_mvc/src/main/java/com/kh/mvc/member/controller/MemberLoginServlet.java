package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 * 
 * 생명주기
 * pageContext: pageContext < request: HttpServletRequest < session: HttpSession < application < ServletContext
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩 처리
		// 1. 사용자입력값처리
		// 2. 업무로직
		// 3. 응답처리 -> jsp위임 | redirect)
		// redirect 요청주소르 변경, 새로고침을 통한 오류를 방지
		
		
		// 0.
		request.setCharacterEncoding("utf-8");
		
		// 1.
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		System.out.println("memberId = " + memberId + ", password = " + password);
		
		// 2. 업무로직: memberId로 회원조회 & 로그인 성공여부 검사
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member@MemberLoginServlet.doPost = " + member);
		
		HttpSession session = request.getSession();
		// 로그인 성공여부
		if(member != null && password.equals(member.getPassword())) {
			// 성공
			
			// 로그인 객체를 session에 저장
			session.setAttribute("loginMember", member);
			session.setAttribute("msg", "로그인 성공");
		}
		else {
			// 실패
			session.setAttribute("msg", "로그인 실패");
		}
		
		// 3.
//		request.getRequestDispatcher("/index.jsp").forward(request, response);
		String location = request.getContextPath() + "/";
		response.sendRedirect(location);

	}

}
