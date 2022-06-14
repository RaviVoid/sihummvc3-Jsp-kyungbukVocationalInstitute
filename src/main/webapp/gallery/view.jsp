<%@page import="domain.BoardVO"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	BoardVO vo = (BoardVO)request.getAttribute("vo");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<table class="table">
		<colgroup>
		<col width="10%">
		<col>
		</colgroup>
		<tr>
			<th>제목</th>
			<td><%=vo.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=vo.getContent() %><br/>
			<img src="upload/<%=vo.getRealSaveFileName() %>" style="max-width:100%;" alt="첨부이미지"/>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><a href="download.jsp?rsfn=<%=vo.getRealSaveFileName() %>&rfn=<%=vo.getRealFileName() %>"><%=vo.getRealFileName() %></a></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=vo.getWriter() %></td>
		</tr>
	</table>
	<div class="pull-right">
	<button id="btn_del" class="btn btn-danger">삭제</button>
	<a href="update?num=<%=vo.getNum() %>" class="btn btn-default">글수정</a>
	</div>
</div>
<form id="frm_del" method="post" action="delete">
<input type="hidden" name="num" value="<%=vo.getNum() %>">
<input type="hidden" name="realSaveFileName" value="<%=vo.getRealSaveFileName() %>">
</form>
<script>
$(document).ready(function(){
	$("#btn_del").on("click", function(e){
		e.preventDefault();
		
		if(confirm("삭제하시겠습니까?")){
			$("#frm_del").submit();
		}		
		
	});
});
</script>
</body>
</html>




