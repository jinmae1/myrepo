package com.kh.mvc.admin.controller;

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
 * Servlet implementation class AdminMemberRoleUpdateServlet
 */
@WebServlet("/admin/memberRoleUpdate")
public class AdminMemberRoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * sql = update member set member_role ? where member_id = ?
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		// 2. 업무로직
		// 3. view단 처리(dml이니까 redirect) => 안그러면 요청이 두 번 갈 수 있다.
		
		HttpSession session = request.getSession();
		

		try {
			String memberId = request.getParameter("memberId");
			String memberRole = request.getParameter("memberRole");
			System.out.println("memberId@servlet = " + memberId);
			System.out.println("memberRole@servlet = " + memberRole);
			
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberRole(memberRole);
			
			// 2.
			int result = memberService.updateMemberRole(member);
			String msg = result > 0 ? "회원권한 변경 성공" : "회원권한 변경 실패";

			session.setAttribute("msg", msg);
			String location = request.getContextPath() + "/admin/memberList";
			response.sendRedirect(location);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
