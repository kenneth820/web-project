<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
회원 인증된 사용자가 이동할 메인 페이지
<br>
<%-- <% request.setCharacterEncoding("UTF-8"); %> --%>

<form method="get" action="logout.do">
<!-- <table border="1"> -->
<table>
	<tr>
		<td>안녕하세요. 
<%-- 			<%= request.getParameter("name") %> --%> 
<%-- 			( <%= request.getParameter("userid") %> ) --%> 
			<%= request.getAttribute("DB_name") %> 
			( <%= request.getAttribute("userid") %> ) 

			회원님 반갑습니다.
		</td>
		
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그아웃">
			<input type="button" value="회원정보변경">
		</td>
	</tr>
</table>
</form>

</body>
</html>