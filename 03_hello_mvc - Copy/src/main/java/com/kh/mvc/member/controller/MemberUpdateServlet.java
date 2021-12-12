package com.kh.mvc.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.common.MvcUtils;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 인코딩
		// 2. 사용자 입력값 처리 -> Member 객체 생성
		// 3. 업무로직 요청: 서비스 객체의 updateMember 호출 & Member 객체 전달
		// 4. 리다이렉트 처리 및 사용자 메시지 준비

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		try {
			String memberId = request.getParameter("memberId");
			String memberName = request.getParameter("memberName");
			String gender = request.getParameter("gender");
			String _birthday = request.getParameter("birthday");
			Date birthday = "".equals(_birthday) ? null : Date.valueOf(_birthday);
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String[] hobbies = request.getParameterValues("hobby");
			String hobby = hobbies != null ? String.join(",", hobbies) : "";

			Member member = new Member(memberId, null, memberName, MemberService.USER_ROLE, gender, birthday, email,
					phone, address, hobby, null);
			int result = memberService.updateMember(member);
			String msg = result > 0 ? "회원정보 수정 성공" : "회원정보 수정 실패";
			if(result > 0 ) {
				Member updateMember = memberService.selectOneMember(memberId);
				session.setAttribute("loginMember", updateMember);
			}

			session.setAttribute("msg", msg);
			String location = request.getContextPath() + "/member/memberView";
			response.sendRedirect(location);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}

}
