<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	BoardDTO cont = (BoardDTO)request.getAttribute("Content");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="30%" color="lightgray">
			<h2>게시물 상세정보</h2>
		<hr width="30%" color="lightgray">
		<br> <br>
		
		
		<table border="1" width="300">
		
			<tr>
				<th>작성자</th>
				<td>
					<%=cont.getBoard_writer() %>
				</td>
			</tr>
			
				<tr>
				<th>글제목</th>
				<td>
					<%=cont.getBoard_title()  %>
				</td>
			</tr>
			
				<tr>
				<th>글내용</th>
				<td>
					<%=cont.getBoard_cont()  %>
				</td>
			</tr>
			
				<tr>
				<th>글 비밀번호</th>
				<td>
					<%=cont.getBoard_pwd()  %>
				</td>
			</tr>
		
		</table>
		<br>
		
		<input type="button" value="글수정"
			onclick="location.href='modify.go?no=<%=cont.getBoard_no() %>'">&nbsp;&nbsp;&nbsp;
			
		<input type="button" value="글삭제"
			onclick="if(confirm('정말로 게시글을 삭제 하시겠습니까?')){
				location.href='view/board_delete.jsp?no=<%=cont.getBoard_no() %>'
			}else {return; }">
			
		<input type="button" value="게시글목록"
			onclick="location.href='list.go'">
		
		
	
	</div>

</body>
</html>