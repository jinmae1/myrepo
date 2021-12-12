package com.kh.mvc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {

	/**
	 * 파라미터를 HttpServletRequest, HttpServletResponse 타입으로 변환이 가능하다.
	 * 현재는 Http가 없는 부모타입이다. 
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 전처리
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uri = httpRequest.getRequestURI();
		String queryString = httpRequest.getQueryString();
//		System.out.println("=============================");
//		System.out.println(uri);
//		if(queryString != null)
//			System.out.print(queryString);
//		System.out.println("-----------------------------");
		
		// 다음 필터 또는 Servlet연결을 위해 반드시 호출
		/* FilterChain chain */ chain.doFilter(request, response);
		
		// 후처리
//		System.out.println("_____________________________");
//		System.out.println();
		
	}

}
