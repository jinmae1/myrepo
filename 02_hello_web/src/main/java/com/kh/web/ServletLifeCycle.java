package com.kh.web;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletLifeCycle
 * Servlet 객체의 생명주기
 * - @webServlet("/ServletLifeCycle") 서블릿 생성 & 등록
 * - singleton pattern으로 운영됨. 프로그램 운영 중에 딱 하나의 객체만 만들어져서 처리됨
 * - tomcat에 의해 딱 한번 객체 생성 후 재사용됨
 * 
 * 1. 생성자 호출
 * 2. @PostConstruct
 * 3. init 메소드
 * 
 * 4. service(HttpServletRequest, HttpServletResponse)
 * 	- doGet(HttpServletRequest, HttpServletResponse)
 * 	- doPost(HttpServletRequest, HttpServletResponse)
 * 
 * 5. destroy
 * 6. @PreDestroy
 *  
 */
@WebServlet("/ServletLifeCycle")
public class ServletLifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLifeCycle() {
        super();
        System.out.println("ServletLifeCycle 생성자 호출");
    }

    
    /**
     * servlet 객체 생성 "이후" 호출
     */
    @PostConstruct
    public void postConstruct() {
    	System.out.println("[@PostConstruct 호출]");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("[init 호출]");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * service 메소드 -> 전송방식에 따라 doGet 또는 doPost가 호출
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("[doGet 호출] hash: " + this.hashCode());
	}

	/**
	 * 객체의 자원해제
	 */
	public void destroy() {
		System.out.println("[destroy 호출]");
	}
	
	/**
	 * "실제" 자원반납 "이전"에 호출 
	 */
	@PreDestroy
	public void preDestroy() {
		System.out.println("[@PreDestroy 호출");
	}
}