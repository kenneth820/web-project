package com.kenneth.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBManager;

@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/join.jsp");		// 페이지 이동
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		// post 방식 한글 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");
		
//		System.out.println("이름: " + name);		
		out.println(name);
		out.println(userid);
		out.println(pwd);
		out.println(email);
		out.println(phone);
		out.println(admin);

//		String sql_insert = "insert into member values('" + name + "','" + userid 
//													+ "','" + pwd + "','" + email 
//													+ "','" + phone + "'," + admin + ")";
		String sql_insert_pstmt = "insert into member values(?, ?, ?, ?, ?, ?)";
													
//		System.out.println(sql_insert);
//		System.out.println(sql_insert_pstmt);
		
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			// 1. jdbc 드라이버 로드 : forName(className)
			// 2. 디비 접속을 위한 연결 객체 생성 : getConnection(url, user, password)
			conn = DBManager.getConnection();
	
			// 3. 쿼리문을 실행하기 위한 객체 생성
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql_insert_pstmt);
			pstmt.setString(1, name);
			pstmt.setString(2, userid);
			pstmt.setString(3, pwd);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			pstmt.setString(6, admin);
			
			// 4. 쿼리 실행 및 결과 처리
			// executeQuery(sql)	- select
			// executeUpdate(sql)	- insert update delete	
//			int result = stmt.executeUpdate("insert into member values('장보고', 'jang', '1234', 'bogo@nate.com','01014785236', 0)");
//			int result = stmt.executeUpdate(sql_insert);
			int result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		// 회원 가입 성공 - 디비에 성공적으로 저장 완료
		
		// 회원 가입 실패 - 디비에 저장 실패
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/login.jsp");
		dispatcher.forward(request, response);

	}
}
