package com.kh.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestPerson3Servlet
 */
@WebServlet("/testPerson3.do")
public class TestPerson3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ===============================================
		// 0. 인코딩처리
		// 1. 요청처리: 클라이언트의 사용자입력값 -> 자바변수로 옮기기
		// 2. 업무로직
		// 3. 응답처리: jsp 위임(RequestDispatcher객체의 forward)
		//				jsp에서 사용할 데이터를 속성으로 저장
		// ===============================================
		
		// 0. 인코딩처리
		request.setCharacterEncoding("utf-8");
		
		// 1. 요청처리
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		String[] foods = request.getParameterValues("food");
		String food = foods != null ? Arrays.toString(foods) : "없음";
		
		System.out.println("name = " + name);
		System.out.println("color = " + color);
		System.out.println("animal = " + animal);
		System.out.println("food = " + food);
		
		// 2. 업무로직
		String todaysRecommend = "";
		String style = "";
		switch(color) {
			case "빨강" : todaysRecommend = "빨간 떡볶이"; style="red"; break;
			case "노랑" : todaysRecommend = "노란 카레"; style="yellow"; break;
			case "초록" : todaysRecommend = "초록 젤리"; style="green"; break;
			case "파랑" : todaysRecommend = "파란 캔디바"; style="blue"; break;
		}
		
		//3. 응답처리
		request.setAttribute("todaysRecommend", todaysRecommend);
		request.setAttribute("style", style);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/testPersonResult.jsp"); // "/" === "/src/main/webapp"
		requestDispatcher.forward(request, response);
		
	}

}
