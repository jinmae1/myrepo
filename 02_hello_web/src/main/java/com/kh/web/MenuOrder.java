package com.kh.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuOrder extends HttpServlet {

	private static final int cow = 5_000;
	private static final int rice = 4_500;
	private static final int cheese = 4_000;

	private static final int potato = 1_500;
	private static final int onion = 1_700;

	private static final int coke = 1_000;
	private static final int cider = 1_000;
	private static final int coffee = 1_500;
	private static final int milkshake = 2_500;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String mainMenu = request.getParameter("mainMenu");
		String sideMenu = request.getParameter("sideMenu");
		String drinkMenu = request.getParameter("drinkMenu");

		int price = calculatePrice(mainMenu, sideMenu, drinkMenu);
		System.out.println(price + "원");

		request.setAttribute("mainMenu", mainMenu);
		request.setAttribute("sideMenu", sideMenu);
		request.setAttribute("drinkMenu", drinkMenu);
		request.setAttribute("price", String.valueOf(price));

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/menuEnd.jsp");
		requestDispatcher.forward(request, response);

	}

	private int calculatePrice(String main, String side, String drink) throws IOException {
		int totalPrice = 0;

		switch(main) {
			case "한우버거": totalPrice += cow; break;
			case "밥버거": totalPrice += rice; break;
			case "치즈버거": totalPrice += cheese; break;
			default: throw new IOException("Main menu");
		}

		switch(side) {
			case "감자튀김": totalPrice += potato; break;
			case "어니언링": totalPrice += onion; break;
			default: throw new IOException("Side menu");
		}

		switch(drink) {
			case "콜라": totalPrice += coke; break;
			case "사이다": totalPrice += cider; break;
			case "커피": totalPrice += coffee; break;
			case "밀크쉐이크": totalPrice += milkshake; break;
			default: throw new IOException("Drink menu");
		}

		return totalPrice;
	}
}