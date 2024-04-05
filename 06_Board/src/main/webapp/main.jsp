<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="30%" color="blue">
			<h3>Board 테이블 메인 페이지</h3>
		<hr width="30%" color="blue">
		<br> <br>
		
		<%-- request.getContextPath() : 현재 프로젝트명을 문자열로 반환해 주는 메서드 --%>
		<a href="<%=request.getContextPath() %>/list.go">[전체 게시물 목록]</a>
	
	</div>

</body>
</html>