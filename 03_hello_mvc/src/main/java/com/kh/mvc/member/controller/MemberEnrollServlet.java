package com.kh.mvc.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) GET /mvc/member/memberEnroll 회원가입 폼을 제공
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) POST /mvc/member/memberEnroll DB에 레코드 기록
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("request@MemberEnrollServlet.doPost = " + request);
		
		request.setCharacterEncoding("utf-8");
		
		try {
			String memberId = request.getParameter("memberId");
			String password= request.getParameter("password");
			String memberName = request.getParameter("memberName");
			String gender = request.getParameter("gender");
//			Date _birthday = java.sql.Date.valueOf(request.getParameter("birthday"));
			String _birthday = request.getParameter("birthday");
			Date birthday = "".equals(_birthday) ? null : Date.valueOf(_birthday);
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String[] hobbies = request.getParameterValues("hobby");
			String hobby = hobbies != null ? String.join(",", hobbies): "";
			
			Member member = new Member(memberId, password, memberName, MemberService.USER_ROLE, gender, birthday, email, phone, address, hobby, null);
			int result = memberService.insertMember(member);
			String msg = result > 0 ? "회원가입 성공" : "회원가입 실패";
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			String location = request.getContextPath() + "/";
			response.sendRedirect(location);
		} catch (IOException e) {
			e.printStackTrace();
			throw e; // tomcat이 error.jsp로 위임하도록 처리
		}
		
	}

}
