package controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardInfo;
import domain.BoardVO;
import service.ListServiceImpl;
import service.WriterService;
import service.WriterServiceImpl;

/**
 * Servlet implementation class WriterController
 */
@WebServlet("/list")
public class ListController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      
      ListServiceImpl service = new ListServiceImpl();
      
      //페이징
      int pageCount = 5;
      int pageRow = 20;//페이지당 보여줄 게시물 갯수
      int pageNum = 1;
      int pagingNum = 5;
      
      if(request.getParameter("pageNum") != null){
         pageNum = Integer.parseInt(request.getParameter("pageNum"));
      }
      
      int startPage = (pageNum - 1) * pageRow;

      
//      String field = request.getParameter("field");
//      if(field == null) {
//         field = "title";
//      }
      
      String keyWord = request.getParameter("keyWord");
      if(keyWord == null) {
         keyWord = "";
      }
      String keyWord2 = request.getParameter("keyWord2");
      if(keyWord2 == null) {
         keyWord2 = "";
      }
      String keyWord3 = request.getParameter("keyWord3");
      if(keyWord3 == null) {
         keyWord3 = "";
      }
      
      BoardInfo boardInfo = service.boardInfo(startPage, pageRow, keyWord, keyWord2, keyWord3);
      
      //글번호 넘버링
      int totalNum = boardInfo.getTotalRow()-((pageNum - 1) * pageRow);
      
      //페이징
      int startNum = (((pageNum-1)/pagingNum)*pagingNum)+1;

      request.setAttribute("pagingNum", pagingNum);
      request.setAttribute("startNum", startNum);

      request.setAttribute("pageRow", pageRow);
      
      request.setAttribute("totalNum", totalNum);
      request.setAttribute("boardInfo", boardInfo);
      request.setAttribute("pageNum", pageNum);

//      request.setAttribute("field", field);
      request.setAttribute("keyWord", keyWord);
      request.setAttribute("keyWord2", keyWord2);
      request.setAttribute("keyWord3", keyWord3);
      
//      Collection<BoardVO> list = service.read();
//      int totalRow = service.totalRow();
//      
//      request.setAttribute("list", list);
//      request.setAttribute("totalRow", totalRow);
      
      
      RequestDispatcher dispatcher = request.getRequestDispatcher("gallery/list.jsp");
      dispatcher.forward(request, response);
   }


}


