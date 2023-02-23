package bitcamp.myapp.servlet.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.BoardFileDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardFile;
import bitcamp.myapp.vo.Member;
//multipart/form-data를 처리할 때 Servlet3.0 기본라이브러리를 사용한다면
//서블릿은 다음 애노테이션을 붙여야한다.
import bitcamp.util.TransactionManager;
@MultipartConfig(maxFileSize = 1024*1024*50)
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private TransactionManager txManager;
  private BoardDao boardDao;
  private BoardFileDao boardFileDao;

  @Override
  public void init() {
    ServletContext ctx = getServletContext();
    boardDao = (BoardDao) ctx.getAttribute("boardDao");
    boardFileDao = (BoardFileDao) ctx.getAttribute("boardFileDao");
    txManager = (TransactionManager) ctx.getAttribute("txManager");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    txManager.startTransaction();
    try {
      // 로그인 사용자의 정보를 가져온다.
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");

      Board board = new Board();
      board.setNo(Integer.parseInt(request.getParameter("no")));
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));

      Board old = boardDao.findByNo(board.getNo());



      if (old.getWriter().getNo() != loginUser.getNo()) {
        response.sendRedirect("../auth/fail");
        return;
      } if (boardDao.update(board) == 0) {
        request.setAttribute("error", "data");
      }
      //게시글에 첨부파일 추가하기
      Collection<Part> parts = request.getParts();
      List<BoardFile> boardFiles = new ArrayList<>();

      for (Part part : parts) {
        if(!part.getName().equals("files") ||part.getSize()==0){
          continue;
        }
        String filename = UUID.randomUUID().toString();

        // 임시 저장된 첨부파일을 특정 디렉토리로 옮긴다.
        // 이때 전체 경로 및 파일명을 File 객체에 담아 넘겨야 한다.
        // 1) 서블릿 컨테이너가 실행하는 현재 웹 애플리케이션의 실제 경로 알아내기
        String realPath = this.getServletContext().getRealPath("/board/upload/" + filename);
        System.out.println(realPath);
        part.write(realPath);

        BoardFile boardFile = new BoardFile();
        boardFile.setOriginalFilename(part.getSubmittedFileName());
        boardFile.setFilepath(filename);
        boardFile.setMimeType(part.getContentType());
        boardFile.setBoardNo(board.getNo());
        //boardFileDao.insert(boardFile);
        boardFiles.add(boardFile);
      }
      if(boardFiles.size() >0) {
        boardFileDao.insertList(boardFiles);
      }
      txManager.commit();

    }catch (Exception e) {
      txManager.rollback();
      e.printStackTrace();
      request.setAttribute("error", "data");
    }

    request.getRequestDispatcher("/board/update.jsp").forward(request, response);
  }
}



