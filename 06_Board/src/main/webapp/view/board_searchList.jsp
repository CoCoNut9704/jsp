<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	List<BoardDTO> boards = (List<BoardDTO>)request.getAttribute("Search");
	System.out.println(boards.get(0).getBoard_title());
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
			<h3>BOARD 테이블 검색 목록 페이지</h3>
		<hr width="30%" color="lightgray">
		<br> <br>
		
		<table border="1" width="450">
			<tr>
				<th>게시글 No.</th> <th>게시글 제목</th>
				<th>작성자</th> <th>조회수</th> <th>작성일자</th>
			</tr>
		
		<%
			if(boards.size() !=0) {
				for(int i=0; i<boards.size(); i++){
					
					BoardDTO board = boards.get(i);
		%>	
			<tr>
				<td> <%=board.getBoard_no() %> </td>
				<td> <%=board.getBoard_title() %> </td>
				<td> <%=board.getBoard_writer() %> </td>
				<td> <%=board.getBoard_hit() %> </td>
				<td> <%=board.getBoard_update() %> </td>
			</tr>
		
		<% 	} // for문 end
		}else {
		 
		%>
			<tr>
				<td colspan="4" align="center">
					<h3>검색된 게시글 목록이 없습니다...</h3>
				</td>
			</tr>
			<% }
			%>
		
		</table>
		<br>
		
		<input type="button" value="게시글목록"
			onclick="location.href='select.go'">

	</div>

</body>
</html>