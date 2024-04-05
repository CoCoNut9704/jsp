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

@WebServlet("/modify_ok")
public class ModifyOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ModifyOkServlet() {
        super();
    
    }
    	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정 폼 페이지에서 넘어온 글번호에 해당하는 데이터들을 
		// board 테이블에 수정시키는 비지니스 로직.
		
		// 한글 깨짐 방지 작업 설정.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 : 수정 폼 페이지에서 넘어온 데이터들을 받아 주어야 한다.
		String board_wirter = request.getParameter("writer").trim();
		
		String board_title =  request.getParameter("title").trim();
		
		String board_content = request.getParameter("content").trim();
		
		String board_pwd = request.getParameter("pwd").trim();
		
		// type="hiddent"으로 설정된 데이터들도 받아 주어야 한다.
		int board_no = Integer.parseInt(request.getParameter("num").trim());
		
		String db_pwd = request.getParameter("db_pwd").trim();
		
		// 2단계 : 1단계에서 넘어온 데이터들을 DTO 객체에 저장해 주자.
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_writer(board_wirter);
		
		dto.setBoard_writer(board_pwd);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		PrintWriter out = response.getWriter();
		
		if(board_pwd.equals(db_pwd)) {
			
			int check = dao.updateBoard(dto);
			
			if(check > 0) {
				out.println("<script>");
				out.println("alert('게시글 수정 성공!!!')");
				out.println("location.href='content.go?no=" + dto.getBoard_no()+"'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('게시글 수정 실패;;;')");
				out.println("history.back()");
				out.println("</script>");	
			}
			
		}else {
			// 비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인해 주세요.')");
			out.println("history.back()");
			out.println("</script>");	
		}
		
		
	}

}
