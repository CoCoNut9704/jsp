package com.board.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;






public class BoardDAO {
   


   //DB와 연결하는 객체.
   Connection con = null;
   
   PreparedStatement pstmt = null;
   
   ResultSet rs = null;
   
   String sql = null;
   
   // BoardDAO 객체를 싱글턴 방식으로 만들어보자.
   // 1단계 : BoardDAO 객체를 정적 멤버로 선언해주자.
   private static BoardDAO instance = null;
   
   // 2단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로 기본생성자의 접근지정자를 public이 아닌 private으로 변경해 주어야 함.
   //        즉, 외부에서 직접적으로 기본생성자에 접근하여 호출하지 못하도록 하는 방법이다.
   public BoardDAO() {
          
   }
   
   // 기본 생성자 대신에 싱글턴 객체를 return 해주는 getInstance() 메서드를 만들어서 해당 getInstance() 메서드를 외부에서 접근가능하도록 해줘야 함.
   public static BoardDAO getInstance() {
      
      if(instance == null) {
         
         instance = new BoardDAO();
                       
      }
      return instance;
   }
   
   // DB 연동을 작업을 하는 메서드
   public void openConn() {
      
      String driver = "oracle.jdbc.driver.OracleDriver";
      
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      
      String user = "goott";
      
      String password = "99229922";
      
      try {
         Class.forName(driver);
               
         con = DriverManager.getConnection(url, user, password);
               
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
         
   }
      // DB에 연결되어 있던 자원을 종료하는 메서드
      public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
         
          try {
             
             if(rs != null) {rs.close();}
             if(pstmt != null) {pstmt.close();}
             if(con != null) {con.close();}
             
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         
      }
    public void closeConn(PreparedStatement pstmt, Connection con) {
       
       try {
                
          if(pstmt != null) {pstmt.close();}
          if(con != null) {con.close();}
          
       } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }
   
}

   public int getBoardCount() {
      
      int count = 0;
      
      try {
         
         openConn();
         
         sql ="select count(*) from board";
         
         pstmt = con.prepareStatement(sql);
         
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            count = rs.getInt(1);
         }   
         
      } catch (SQLException e) {
         
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         
         closeConn(rs, pstmt, con);
      }
      return count;
      
   }
   
   public List<BoardDTO> getBoardList() {
      
      List<BoardDTO> list = new ArrayList<BoardDTO>();

      
      try {
         
         openConn();
         
         sql = "select * from board order by board_no desc";
         
         pstmt = con.prepareStatement(sql);
         
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            
            BoardDTO dto = new BoardDTO();
            
            dto.setBoard_no(rs.getInt("board_no"));
            dto.setBoard_writer(rs.getString("board_writer"));
            dto.setBoard_title(rs.getString("board_title"));
            dto.setBoard_cont(rs.getString("board_cont"));
            dto.setBoard_pwd(rs.getString("board_pwd"));
            dto.setBoard_hit(rs.getInt("board_hit"));
            dto.setBoard_date(rs.getString("board_date"));
            dto.setBoard_update(rs.getString("board_update"));
            
            list.add(dto);
            
         } 
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         
         closeConn(rs, pstmt, con);
      }return list;
      
   } // getBoardList() 메서드 end
   
   // Board 테이블에 게시글을 추가하는 메서드.
   public int insertBoard(BoardDTO dto) {
	   
	   int result = 0, count = 0;
	      
	   try {
		   
		   openConn();
		   
		   sql = "select max(board_no) from board";
		   
		   pstmt = con.prepareStatement(sql);
		   
		   rs = pstmt.executeQuery();
		   
		   if(rs.next()) {
		   
		   count = rs.getInt(1) + 1;
		 }  
		   
		   sql = "insert into board values(?, ?, ?, ?, ?, default, sysdate, '')";
		   
		   pstmt = con.prepareStatement(sql);
		   
		   pstmt.setInt(1, count);
		   pstmt.setString(2, dto.getBoard_writer());
		   pstmt.setString(3, dto.getBoard_title());
		   pstmt.setString(4, dto.getBoard_cont());
		   pstmt.setString(5, dto.getBoard_pwd());
		   
		  result = pstmt.executeUpdate();
		   
		   
	   	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		closeConn(rs, pstmt, con);
	}
	   
	   return result;
	   
   } // insertBoard() 메서드 end
   
   
   // board 테이블의 게시글 번호에 해당하는 게시글의
   // 조회 수를 증가시켜 주는 메서드
   public void readCount(int num) {
	   
	   
	   
	   try {
		   
	    openConn();
		   
	    sql = "update board set board_hit = board_hit + 1 where board_no = ?";
	    
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, num);
		
		pstmt.executeUpdate();
			
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		closeConn(pstmt, con);
	}
	   
   } // readCount() 메서드 end
   
   // 글 번호에 해당하는 게시글을 조회하는 메서드.
   public BoardDTO contentBoard(int no) {
	             
	   BoardDTO dto = null;
	   
	   
	   
	   try {
		   
		   openConn();
		   
		   sql = "select * from board where board_no =?";
		   
		   pstmt = con.prepareStatement(sql);
		   
		   pstmt.setInt(1, no);
		   
		   rs = pstmt.executeQuery();
		   
		   if(rs.next());
		   
		   dto = new BoardDTO();
		   
		   dto.setBoard_no(rs.getInt("board_no"));
           dto.setBoard_writer(rs.getString("board_writer"));
           dto.setBoard_title(rs.getString("board_title"));
           dto.setBoard_cont(rs.getString("board_cont"));
           dto.setBoard_pwd(rs.getString("board_pwd"));
           dto.setBoard_hit(rs.getInt("board_hit"));
           dto.setBoard_date(rs.getString("board_date"));
           dto.setBoard_update(rs.getString("board_update"));
		   
		   
	} catch (SQLException e) {
		
		e.printStackTrace();
	} finally {
		
		closeConn(rs, pstmt, con);
	}
	   return dto;
	   
	   
   } // contentBoard() 메서드 end
   
   	// board 테이블의 글번호에 해당하는 게시글을 수정하는 메서드.
   public int updateBoard(BoardDTO dto) {
	   
	   int result = 0;
	   
	  
	   
	   try {
		   
		   openConn();
		   
		   sql = "update board set board_title =?,"
		   		+ "board_cont = ?, "
				+ "board_update = sysdate"
				+ " where board_no =?";
		   
		   pstmt = con.prepareStatement(sql);
		   
		   pstmt.setString(1, dto.getBoard_title());
		   pstmt.setString(2, dto.getBoard_cont());
		   pstmt.setInt(3, dto.getBoard_no());
		   
		   result = pstmt.executeUpdate();
		   
	} catch (SQLException e) {
	
		e.printStackTrace();
	} finally {
		closeConn(pstmt, con);
	}
	return result;
	   
   }  // updateBoard() 메서드 end
   
   // board 테이블에서 게시글 번호에 해당하는 게시글을 삭제하는 메서드.
   public int deleteBoard(int no, String pwd) {
	   
	   int result = 0;
	   
	   
	   
	   try {
		   
		   openConn();
		   
		   sql = "select * from board where board_no = ?";
		   
		   
		   pstmt = con.prepareStatement(sql);
		   
		   pstmt.setInt(1, no);
		   
		  rs = pstmt.executeQuery();
		  
		  
		  
		  if(rs.next()); {
			  
		  
		  
		  if(pwd.equals(rs.getString("board_pwd"))) {
			  
			  sql = "delete from board where board_no = ?";
			  
			  pstmt = con.prepareStatement(sql);
			  
			  pstmt.setInt(1, no);
			  
			  result = pstmt.executeUpdate();
			  
			  
			  
		  }else {
			  result = -1;
		  }
		}	   
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		closeConn(rs, pstmt, con);
	}
	return result;
   } // deleteBoard() 메서드 end
   
   public void updateSequence(int no) {
	   
	 
	   
	   try {
		   
		   openConn();
		   
		   sql = "update board set "
		   		+ "board_no = board_no -1"
		   		+ " where board_no > ?";
		   
		   pstmt = con.prepareStatement(sql);
		   
		   pstmt.setInt(1, no);
		   
		   pstmt.executeUpdate();
		   
		   
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		closeConn(pstmt, con);
	}
	   
	   
   } // updateSequence() 메서드 end

public List<BoardDTO> searchBoardList(String search_field, String search_keyword) {
	
	List<BoardDTO> dto =  new ArrayList<BoardDTO>();
	
	
			
	try {
		
		openConn();
		
		sql = "select * from board ";
		
		if(search_field.equals("title")) {
			sql += "where board_title like ? ";
		}else if(search_field.equals("writer")) {
			sql += "where board_writer like ?";
		}else if(search_field.equals("cont")) {
			sql += "where board_cont like ?";
		}else {
			sql += "where (board_title || board_cont) like ?";
		}
		
		sql += "order by board_no desc";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, "%"+search_keyword+"%");
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			BoardDTO dto1 = new BoardDTO();
			
			dto1.setBoard_no(rs.getInt("board_no"));
	        dto1.setBoard_writer(rs.getString("board_writer"));
	        dto1.setBoard_title(rs.getString("board_title"));
	        dto1.setBoard_cont(rs.getString("board_cont"));
	        dto1.setBoard_pwd(rs.getString("board_pwd"));
	        dto1.setBoard_hit(rs.getInt("board_hit"));
	        dto1.setBoard_date(rs.getString("board_date"));
            dto1.setBoard_update(rs.getString("board_update"));
			
			dto.add(dto1);
			
		}
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		closeConn(pstmt, con);
	}
	return dto;
	
	
	
}
   
   


}