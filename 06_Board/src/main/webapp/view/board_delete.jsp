<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

    		int board_no=Integer.parseInt(request.getParameter("no").trim());
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="30%" color="gray">
			<h3>BOARD 테이블 게시글 삭제 폼 페이지</h3>
		<hr width="30%" color="gray">
		<br> <br>
		
		<form method="post"
			action="<%=request.getContextPath() %>/delete_ok.go">
			
			<input type="hidden" name="no" value="<%=board_no %>">
			
			<table border="1" width="400">
				<tr>
					<th>삭제할 게시글 비밀번호</th>
					<td>
						<input type="password" name="pwd">
					</td>	
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글삭제">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>	
				</table>
		
			</form>

	</div>

</body>
</html>