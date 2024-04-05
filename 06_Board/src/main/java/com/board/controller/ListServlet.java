package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;


@WebServlet("/list.go")
public class ListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       

    public ListServlet() {
        super();
        
    }

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 요청 : 전체 게시물 목록을 보여달라고 요청.
      // 응답 : board 테이블에서 게시글 전체 목록을 조회하여 조회된 게시물 전체를 view 페이지로 이동시키는 비지니스 로직
      
      BoardDAO dao = BoardDAO.getInstance();
      
      // 게시물의 총 갯수를 구하는 메서드 호출.
      int count = dao.getBoardCount();
      
      // 게시물의 전체 리스트를 구하는 메서드 호출.
      
      List<BoardDTO> boardList =  dao.getBoardList();
      
      // 조회된 게시물 목록과 게시물 수를 view page로 이동.
      request.setAttribute("List", boardList);
      request.setAttribute("Count", count);
      
      request.getRequestDispatcher("view/board_list.jsp").forward(request, response);
      
      
   }

}