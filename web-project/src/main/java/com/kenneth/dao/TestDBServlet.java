package com.kenneth.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		// post 방식 한글 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();		
		
		// 데이터베이스 연동 코드
		try {

			// 1. jdbc 드라이버 로드
			// forName(className)
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			// 2. 디비 접속을 위한 연결 객체 생성
			// getConnection(url, user, password)
			Connection conn;
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:orcl",
						"ora_user", 
						"1234");
	
			// 3. 쿼리문을 실행하기 위한 객체 생성
			Statement stmt = conn.createStatement();
	
			// 4. 쿼리 실행 및 결과 처리
			// executeQuery(sql)	- select
			// executeUpdate(sql)	- insert update delete
	
	//		request.getParameter("name");
	//		request.getParameter("userid");
	//		request.getParameter("pwd");
	//		request.getParameter("email");
	//		request.getParameter("phone");
	//		request.getParameter("admin");
	
			String sql_select = "select * from member";
			//String sql_select_id = "select * from member where userid = 'somi'";
			/* String sql_insert = "insert into member values(" + name + ", " + userid + ", " + pwd + "," + email + "," + phone + "," + admin + ")"; */
	
			ResultSet rs = stmt.executeQuery(sql_select);
	
			/* stmt.executeUpdate(sql_insert);
			stmt.executeUpdate("update member set email='kenneth820@naver.com' where userid='kenneth'");
			stmt.executeUpdate("delete from member where userid = 'hodong'");
			 */
			while(rs.next()){
				out.print("<h6>" + rs.getString("name") + rs.getString("userid") + "</h6>");
			}
	//			rs = stmt.executeQuery(sql_select_id);
	//			while(rs.next()){
	//				out.print("<h6>" + rs.getString("name") + rs.getString("userid") + "</h6>");
	//				request.setAttribute("name", rs.getString("name"));
	//				request.setAttribute("id", rs.getString("userid"));
	//			}
	
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
