<%@page import="com.kh.mvc.board.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<h2>게시판 </h2>
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th><%--첨부파일이 있는 경우 /images/file.png 표시 width:16px --%>
			<th>조회수</th>
		</tr>
		
<%
	List<Board> list = (List<Board>) request.getAttribute("list");
	for(Board board: list) {
%>

	<tr>
		<td><%= board.getNo() %></td>
		<td><%= board.getTitle() %></td>
		<td><%= board.getWriter() %></td>
		<td><%= board.getRegDate() %></td>
		<td></td>
		<td><%= board.getReadCount() %></td>
	</tr>
<%
	}
%>
	</table>

	<div id='pageBar'<%= request.getAttribute("pagebar") %>></div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
