package com.kh.mvc.member.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		String saveId = request.getParameter("saveId");
		System.out.println("memberId = " + memberId + ", password = " + password + ", saveId = " + saveId);
		
		// 2. 업무로직: memberId로 회원조회 & 로그인 성공여부 검사
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member@MemberLoginServlet.doPost = " + member);
		
		// Session
//		HttpSession session = request.getSession();
		HttpSession session = request.getSession(true); // create: boolean(true 기본값), session 객체가 존재하면 해당 객체를 리턴, 존재하지 않으면 새로 만들어서 리턴
		System.out.println(session.getId());
		
		// timeout 설정 - web.xml 설정보다 우선순위가 높다.
		session.setMaxInactiveInterval(30 * 60); // 초 단위(현재 30분)
		
		// session 생성 시각
		System.out.println(new Date(session.getCreationTime()));
		
		// 로그인 성공여부
		if(member != null && password.equals(member.getPassword())) {
			// 성공
			
			// 로그인 객체를 session에 저장
			session.setAttribute("loginMember", member);
			session.setAttribute("msg", "로그인 성공");
			
			// 아이디저장 체크박스 처리
			// 브라우저 호환성을 고려해 도메인당 쿠키개수 50개, 하나의 value값은 4kb를 넘지 않도록 하는 것이 좋다.
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath());
			// persistent 영속쿠키: 초 단위로 시간 입력
			// session 쿠키: setMaxAge 설정 안 한 경우
			if(saveId != null) 
				cookie.setMaxAge(7 * 24 * 60 * 60); // 7일 동안 쿠키 보관
			else
				cookie.setMaxAge(0); // 쿠키 삭제 메소드가 없어서 0으로 즉시 삭제

			response.addCookie(cookie);
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
