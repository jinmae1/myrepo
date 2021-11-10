package com.kh.mvc.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.common.MvcUtils;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

/**
 * sql
 * select * from member order by enroll_date desc
 * 
 * Paging Recipe
 * 1. content section
 *  - cPage: 현재 페이지
 *  - numPerPage: 한 페이지의 게시물 수 10
 *  - startnum, endnum
 *  
 * 2. pagebar section
 *  - totalContent 총 게시물 수
 *  - totalPage 12
 *  - pagebarSize 5
 *  - pageNo 증감변수
 *  - pageStart | pageEnd
 *  - url
 * 
 * 
 * Servlet implementation class AdminMemberListServlet
 * @param <Member>
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * sql = select * from member;
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값
		final int numPerPage = 10;
		int cPage = 1;
		try {
			// /admin/memberList로 바로 들어온 경우
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			// 따로 throw 같은걸 안해주면 cPage에 값을 대입하기 전에 여기로 넘어오기 때문에 cPage는 1로 유지가 된다.
		}
		int startNum = (cPage - 1) * numPerPage + 1;
		int endNum = cPage * numPerPage;
		Map<String, Object> param = new HashMap<>();
		param.put("startNum",  startNum);
		param.put("endNum",  endNum);
		
		
		// 2. 업무로직
		// 2-a. content 영역
		List<Member> list = memberService.selectAllMember(param);
		System.out.println("list@servlet = " + list);
		
		// 2-b. pagebar 영역
		int totalContent = memberService.selectTotalMemberCount();
		String url = request.getRequestURI(); // /mvc/admin/memberList
		System.out.println(totalContent);
		System.out.println(url);
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContent, url);
		System.out.println("pagebar@servlet = " + pagebar);
		
		// 3. view단처리
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(request, response);
		
	}

}
