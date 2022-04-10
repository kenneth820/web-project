package com.kenneth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.MemberDAO;

@WebServlet("/checkId.do")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		int result = mDao.confirmID(userid);
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		
		// result(1) : 사용 불가능, result(-1) : 사용 가능 
		if (result == 1) {
			System.out.println("사용 불가능한 아이디 입니다.");
			request.setAttribute("message", "이미 사용중인 아이디 입니다.");
		} else {
			System.out.println("사용 가능한 아이디 입니다.");
			request.setAttribute("message", "사용 가능한 아이디 입니다.");
		}		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/checkId.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
