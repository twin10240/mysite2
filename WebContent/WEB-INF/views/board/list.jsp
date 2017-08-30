<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>		
					
					<c:forEach items="${list }" var="vo">
						<c:choose>
							<c:when test="${vo.depth > 1 }">
								<tr>
									<td>${vo.rowNum }</td>
									<td class="left" style="padding-left:${(vo.depth -1) * 20}px"> 
										<img src="${pageContext.request.contextPath }/assets/images/reply.png">
										<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no }">${vo.title }</a> 
									</td>
									<td>${vo.name }</td>
									<td>${vo.hit }</td>
									<td>${vo.regDate }</td>
									<td>
										<c:if test="${not empty authUser && authUser.no == vo.memberNo }">
											<a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no }" class="del">삭제</a>
										</c:if>
									</td>
								</tr>
							</c:when>
							
							<c:otherwise>
								<tr>
									<td>${vo.rowNum }</td>
									<td class="left"> <a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no }">${vo.title }</a> </td>
									<td>${vo.name }</td>
									<td>${vo.hit }</td>
									<td>${vo.regDate }</td>
									<td>
										<c:if test="${not empty authUser && authUser.no == vo.memberNo }">
											<a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no }" class="del">삭제</a>
										</c:if>
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>
				
				<div class="pager">
					<ul>
						<c:if test="${prePage > 0 }">
							<li><a href="${pageContext.servletContext.contextPath }/board?page=${prePage }">◀</a></li>
						</c:if>
					
						<c:forEach begin="${startNum }" end="${startNum + 4 }" var="i" >
							<c:choose>
								<c:when test="${rowNum == i }">
									<li class="selected"><a href="${pageContext.servletContext.contextPath }/board?page=${i }"> ${i } </a></li>		
								</c:when>
								
								<c:otherwise>
									<li><a href="${pageContext.servletContext.contextPath }/board?page=${i }"> ${i } </a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:if test="${nextPage > 0 }">
							<li><a href="${pageContext.servletContext.contextPath }/board?page=${nextPage }">▶</a></li>
						</c:if>
					</ul>
				</div>		
						
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href="${pageContext.servletContext.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
<%
	if( "success".equals( request.getParameter( "delete" ) ) ) {
%>
	<script>alert( "성공적으로 삭제 하였습니다." );</script>
<%
	}
%>
</html>