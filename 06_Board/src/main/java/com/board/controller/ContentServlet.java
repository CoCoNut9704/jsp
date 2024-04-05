package com.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;


@WebServlet("/content.go")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ContentServlet() {
        super();
        
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘어온 글번호에 해당하는 게시글을
		// board 테이블에서 조회하여 게시글 상세정보를 
		// view page로 이동시키는 비지니스 로직.
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 조회수를 증가시켜 주는 메서드 호출.
		dao.readCount(board_no);
	    
		// 글 번호에 해당하는 게시글의 상세내역을 조회하는 메서드 호출.
		BoardDTO content = dao.contentBoard(board_no);
		
		request.setAttribute("Content", content);
		
		request.getRequestDispatcher("view/board_content.jsp").forward(request, response);
		
	}

}
