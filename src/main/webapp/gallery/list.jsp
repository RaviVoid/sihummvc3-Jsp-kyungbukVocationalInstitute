<%@page import="domain.BoardInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="domain.BoardVO"%>
<%@page import="java.util.Collection"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
BoardInfo boardInfo = (BoardInfo) request.getAttribute("boardInfo");
Collection<BoardVO> list = boardInfo.getList();
int totalRow = boardInfo.getTotalRow();

int totalNum = (Integer) request.getAttribute("totalNum");
int pageNum = (Integer) request.getAttribute("pageNum");
int pageRow = (Integer) request.getAttribute("pageRow");

int pagingNum = (Integer) request.getAttribute("pagingNum");
int startNum = (Integer) request.getAttribute("startNum");

String keyWord = (String) request.getAttribute("keyWord");
String keyWord2 = (String) request.getAttribute("keyWord2");
String keyWord3 = (String) request.getAttribute("keyWord3");

int lastPage = totalRow/pageRow+((totalRow%pageRow == 0)?0:1);
if(pageNum > lastPage || pageNum < 1){
   //response.sendRedirect("http://www.naver.com");
}

//Collection<BoardVO> list = (Collection)request.getAttribute("list");
//int totalRow = (Integer)request.getAttribute("totalRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
   <div class="container">
      <div>
         <div class="pull-left" style="font-size: 20px;">전체글수 : <%=totalRow%></div><br>
         <div>
            <!-- <a href="list" style="font-weight: bold; font-size: 30px; color: red;">메인으로</a> -->
         </div>
         <div class="pull-right" style="width:310px;">
            <%-- <form>
               <select name="field" class="form-control" style="display:inline-block;width:30%">
                  <option value="">전체</option>
                  <option value="title" <%="title".equals(field)?"selected='selected'":"" %>>제목</option>
                  <option value="content" <%="content".equals(field)?"selected='selected'":"" %>>내용</option>
                  <option value="titleContent" <%="titleContent".equals(field)?"selected='selected'":"" %>>제목+내용</option>
               </select>
               <input type="text" name="keyWord" value="<%=keyWord %>" class="form-control" style="display:inline-block; width:50%">
            </form> --%>
            <form>
               <div>
                  제목
                  <input type="text" name="keyWord" value="<%=keyWord %>" class="form-control" style="display:inline-block; width:50%"><br>
               </div>
               <div>
                  내용
                  <input type="text" name="keyWord2" value="<%=keyWord2 %>" class="form-control" style="display:inline-block; width:50%"><br>
               </div>
               <div>
                  작성자
                  <input type="text" name="keyWord3" value="<%=keyWord3 %>" class="form-control" style="display:inline-block; width:50%"><br>
               </div>
                  <button class="btn btn-default">검색</button>
            </form>
         </div>
      </div>
      <div class="row" style="clear: both; padding: 20px 0px;">
            <%
            Iterator<BoardVO> it = list.iterator();
            while (it.hasNext()) {
               BoardVO vo = it.next();
            %>
            <div style="float:left; padding: 5px 15px;">
               <a href="view?num=<%=vo.getNum()%>">
                  <div style="height:170px; overflow: hidden;">
                  <%-- <%=(vo.getRealSaveFileName()!=null && !"".equals(vo.getRealSaveFileName()))?"<img src='upload/sm_"+vo.getRealSaveFileName()+"' style='width:255.99px; ' />":"<img src='noimg.jpg' style='width:255.99px; ' />" %> --%>
                  <img src="<%=(vo.getRealSaveFileName()!=null && !"".equals(vo.getRealSaveFileName()))?"upload/sm_"+vo.getRealSaveFileName():"noimg.jpg"%>" style='width:255.99px; ' />
                  </div>
                  <div><%=vo.getTitle() %></div>
               </a>
            </div>
            <%
            }
            %>
      </div>
      <nav style="text-align: center;">
         <ul class="pagination">
            <%
            if (pageNum == 1) {
            %>
            <li class="disabled">
               <span aria-hidden="true">&laquo;</span>
            </li>
            <%
            } else {
            %>
            <li>
               <a href="?pageNum=<%=pageNum-1 %>" aria-label="Previous"> 
                  <span aria-hidden="true">&laquo;</span>
               </a>
            </li>
            <%
            }
            %>

            <%
            for(int i = startNum; i <= (startNum+pagingNum)-1; i++){
               if(i > lastPage) break;
               if(pageNum == i){
            %>
            <li class="active"><a><%=i%></a></li>
            <%      
               } else {
            %>
            <li><a href="?pageNum=<%=i%>&keyWord=<%=keyWord %>"><%=i%></a></li>
            <%      
               }
            }
            %>

            <%
            if(lastPage == pageNum){
            %>
            <li class="disabled">
               <span aria-hidden="true">&raquo;</span>
            </li>
            <%
            } else {
            %>
            <li>
               <a href="?pageNum=<%=pageNum+1 %>" aria-label="Next"> 
                  <span aria-hidden="true">&raquo;</span>
               </a>
            </li>
            <%
            }
            %>
         </ul>
      </nav>
      <div class="pull-right">
         <a href="writer" class="btn btn-default">글쓰기</a>
      </div>
   </div>
</body>
</html>