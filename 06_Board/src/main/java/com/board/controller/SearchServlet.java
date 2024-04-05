package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;


@WebServlet("/search.go")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchServlet() {
        super();
       
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String search_field = request.getParameter("field").trim();
		
		String search_keyword = request.getParameter("keyword").trim();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		List<BoardDTO> searchList = dao.searchBoardList(search_field, search_keyword);
		
		request.setAttribute("Search", searchList);
		request.getRequestDispatcher("view/board_searchList.jsp")
			.forward(request, response);
		
	}

}
