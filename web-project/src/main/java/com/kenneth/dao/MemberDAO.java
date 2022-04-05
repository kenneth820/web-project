package com.kenneth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBManager;

public class MemberDAO {
	// 데이터 베이스에 접근하여 데이터를 획득하거나 설정
	// CRUD를 메서드로 구현한 클래스
	
	// Create (insert) - 회원 가입
	public int insertMember(String name, String userid, String pwd, String email, String phone, String admin) {
		String sql_insert_pstmt = "insert into member values(?, ?, ?, ?, ?, ?)";
		
		int result = -1;
		
		Connection conn = null;
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
			// executeUpdate(sql)	- insert update delete	
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;		
	}
	
	
	// Read (select) - 사용자 인증
	// 데이터 베이스에서 회원 정보를 select 하여 값을 비교하고 로그인 수행
	public int checkUser(String userid, String pwd) {		
		String sql_select_pstmt = "select pwd, name from member where userid=?";
		
		int result = -1;
		String name;
		
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
					result = 1;		// pwd 일치
				} else {
					//암호 불일치
					// 비밀번호가 맞지 않습니다.
					result = 0;
					// 존재하지 않는 회원 입니다.
					result = -1;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}		
		return result;
	}
	
	// Read (select) - 데이터 활용
	public void getMember() {
		String sql_select_info = "select * from member where userid=?";
	}
	
	
	// Update (update) - 회원 정보 수정
	public void updateMember() {
		String sql_update = "update";
	}
	
	
	// Delete (delete) - 회원 삭제
	public void deleteMember() {
		String sql_delete = "delete";
	}
	
}
