package com.kenneth.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBManager;

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

		// 포워딩 방식으로 페이지 이동
		String url = "member/login.jsp";	
		
		// 데이터 베이스에서 회원 정보를 select 하여 값을 비교하고 로그인 수행
		String sql_select_pstmt = "select pwd, name from member where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();

			// 3. 쿼리문을 실행하기 위한 객체 생성
			pstmt = conn.prepareStatement(sql_select_pstmt);
			pstmt.setString(1, userid);

			// 4. 쿼리 실행 및 결과 처리
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
//			System.out.println("pwd : " + rs.getString("pwd"));
				if(rs.getString("pwd")!=null && rs.getString("pwd").equals(pwd)) {
					//암호 일치
					request.setAttribute("DB_name", rs.getString("name"));
					request.setAttribute("userid", userid);
					
					url = "main.jsp";
				} else {
					//암호 불일치
					// 비밀번호가 맞지 않습니다.
					// 존재하지 않는 회원 입니다.
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
