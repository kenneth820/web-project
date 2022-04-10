<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복 확인 창</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>

<form action="checkId.do" method="get" name="frm">
아이디 <input type="text" name="userid" value="${userid}">
	<input type="submit" value="중복 체크">
	<br>
	
${message}
	
	<!-- 만약, 사용가능한 아이디인 경우 사용 버튼을 생성 -->
	<c:if test="${result==-1}">
		<input type="button" value="사용" onclick="idOk()">
	</c:if>
		
	<!-- 만약, 사용 불가능한 아이디인 경우 미생성 -->
	<!-- opener : 새로 생성된 윈도우를 연 부모 윈도우 객체 참조 -->
	<c:if test="${result==1}">
		<script type="text/javascript">
			opener.document.frm.userid.value="";
		</script>
	</c:if>

</form>

</body>
</html>