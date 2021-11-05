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
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession();
			
			try {
				Member member = new Member();
				String memberId = request.getParameter("memberId");
				member.setMemberId(memberId);
				int result = memberService.deleteMember(member);
				String msg = result > 0 ? "회원삭제 성공" : "회원삭제 실패";
				if(result > 0) {
					session.invalidate();
				}
				HttpSession newSession = request.getSession();
				newSession.setAttribute("msg", msg);
				String location = request.getContextPath() + "/";
				response.sendRedirect(location);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
			
	 }

}
