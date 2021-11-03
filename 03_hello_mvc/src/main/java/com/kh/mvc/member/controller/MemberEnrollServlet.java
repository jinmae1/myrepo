package com.kh.mvc.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String memberId = request.getParameter("memberId");
		String password= request.getParameter("password");
		String memberName = request.getParameter("memberName");
		String memberRole = "U";
		String gender = request.getParameter("gender");
		Date birthday = java.sql.Date.valueOf(request.getParameter("birthday"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String[] hobbies = request.getParameterValues("hobby");
		String hobby = String.join(",", hobbies);
		
		Member member = new Member(memberId, password, memberName, memberRole, gender, birthday, email, phone, address, hobby, null);
		int result = memberService.insertMember(member);
		
		if(result > 0) {
			String location = request.getContextPath() + "/";
			response.sendRedirect(location);
		}
		
	}

}
