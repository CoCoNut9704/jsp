package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@WebServlet("/insert_ok.go")
public class InsertOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertOkServlet() {
        super();
        
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시판 글 작성 폼 페이지에서 넘어온 데이터들을 
		// board 테이블에 저장하는 비지니스 로직.
		
		// 한글 깨짐 방지 작업 설정.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 : 게시글 입력 폼 페이지에서 넘어온 데이터들을 받아 주어야 한다.
		String board_writer = request.getParameter("writer").trim();
		
		String board_title = request.getParameter("title").trim();
		
		String board_content = request.getParameter("content").trim();
		
		String board_pwd = request.getParameter("pwd").trim();
		
		
		// 2단계 : 1단계에서 넘어온 데이터들을 DTO 객체에 저장해 주자.
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);
		
		// 3단계 : DTO 객체를 DB에 전송해 주어야 함.
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.insertBoard(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('게시글 추가 성공!!!')");
			out.println("location.href='list.go'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시글 추가 실패 ^^ ^^')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
