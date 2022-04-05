package com.kenneth.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kenneth.dao.MemberDAO;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리다이렉트 방식 - 강제 페이지 이동, URL 변경
		// 로그인 페이지로 이동	"member/login.jsp"
//		response.sendRedirect("member/login.jsp");
		
		// 포워드 방식 - URL 유지, request/response 유지
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/login.jsp");
		dispatcher.forward(request, response);
		
//		String name = "kenneth";
//		int age = 12;
//		// 속성 이름으로 속성값을 해당 객체에 저장 
//		request.setAttribute("name", name);
//		request.setAttribute("age", age);
//		
//		
//		String name1 = (String) request.getAttribute("name");
//		int age1 = (Integer) request.getAttribute("age");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		// post 방식 한글 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();		
	
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");		
		out.print(userid);
		out.print(pwd);
		
		MemberDAO mDao = new MemberDAO();

		// 포워딩 방식으로 페이지 이동
		String url = "member/login.jsp";	
		
		
		int result = mDao.checkUser(userid, pwd);
		
		// DB에서 이름을 가져와서 저장하는 구문 작성 - 구현 전 (다음 시간에..)
		String name = mDao.getMember();
		
		
		if (result == 1) {			
			request.setAttribute("DB_name", name);
			request.setAttribute("userid", userid);
			
			request.setAttribute("message", "인증이 완료되었습니다.");
			url = "main.jsp";
		} else if (result == 0) {
			
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		} else {
			
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		}

		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
