package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.vo.Member;

/**
 * Servlet implementation class MemberViewServlet
 */
@WebServlet("/member/memberView")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) GET 요청 전후로 Data의 변경이 없는 경우(ex. 조회) => DQL POST 요청 전후로 Data의
	 *      변경이 있는 경우(ex. 등록/수정/삭제) => DML
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 업무로직
//		HttpSession session = request.getSession();
//		Member loginMember = (Member) session.getAttribute("loginMember");
		// 어차피 session에서 member의 정보를 가져올 수 있기 때문에 상단의 업무로직은 없애고 간단하게 jsp에게 위임하도록 한다.

		// 2. view단 jsp위임
		request.getRequestDispatcher("/WEB-INF/views/member/memberView.jsp").forward(request, response);

	}

}
