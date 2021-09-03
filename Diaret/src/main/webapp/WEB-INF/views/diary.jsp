<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<!-- js import start -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="${ pageContext.request.contextPath }/resources/js/ui/jquery.json-2.2.min.js" type="text/javascript"></script>
	<script src="${ pageContext.request.contextPath }/resources/js/diary.js" type="text/javascript"></script>
	<!-- js import end -->

	<!-- css import start -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/diary.css">
	<!-- css import end -->
	<head>
		<meta charset="UTF-8">
		<title>diary.jsp</title>
	</head>
	<body>
	<div id="leftDiv">
		<!-- left UI -->
		<div id="profile">
			<!-- 닉네임 보여주기 -->
			<p id="nickname">${ userDataMap.USER_NICK }</p>
			<div id="alignCenter">
				<!-- "${ pageContext.request.contextPath }/resources/images/fallguy.jpg" -->
				<c:choose>
					<c:when test="${ empty userDataMap.USER_IMG_EXP }">
						<img id="imgProfile" alt="${ userDataMap }" src="${ pageContext.request.contextPath }/resources/images/defaultProfile.jpg">
					</c:when>
					<c:otherwise>
						<img id="imgProfile" alt="errorDir" src="${ pageContext.request.contextPath }/resources/userData/${ userDataMap.USER_NUM }/profile.${ userDataMap.USER_IMG_EXP }">
					</c:otherwise>
				</c:choose>
			</div>
			<!-- 로그아웃, 회원 정보 수정 페이지 이동 버튼 -->
			<form id="btnForm" method="post">
				<div id="btnProfile">
					<button type="button" class="btn btn-danger" id="logout"><i class="bi bi-box-arrow-left"></i></button>
					<button type="button" class="btn btn" id="modify"><i class="bi bi-gear-fill"></i></button>
				</div>
			</form>
		</div>
		<div id="searchBox">
			<!-- 검색 보이기 -->
			<form id="searchForm" action="goSearch.do">
				<div class="btn-group">
					<button id="selectCategory" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">ALL<span class="caret"></span></button>
					<input id="postCategory" type="hidden" name="postCategory" value="ALL"/>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">ALL</a></li>
						<c:forEach items="${ classifiedPostData }" var="category" varStatus="status">
							<li><a href="#">${ category.key }</a></li>
						</c:forEach>
					</ul>
				</div>
				<div id="inlineElements">
					<input type="text" class="form-control" name="postKeywords" placeholder="제목 또는 내용으로 검색" required/>
					<button type="submit" class="btn btn-info" id="submit">검색</button>
				</div>
			</form>
		</div>
		<div id="fixedLength">
			<div id="postings">
				<!-- 카테고리 및 게시글 목록 보이기 -->
				<div class="container">
					<c:forEach items="${ classifiedPostData }" var="category" varStatus="status">
						<div class="panel-heading">
							<div>
								<a class="fontSize18" data-toggle="collapse" data-parent="#accordion" href="#collapse${ status.count }" >
									<i class='bi bi-folder-fill'></i>${ category.key }
								</a>
							</div>
							<div class="btnEditCategory1" hidden="true">
								<button class="btn btn-info editCategory" type="button"><i class="bi bi-pencil-square"></i></button>
								<button class="btn btn-warning deleteCategory" type="button"><i class="bi bi-trash-fill"></i></button>
							</div>
							<div class="btnEditCategory2" hidden="true">
								<button class="btn btn-success editSubmit" type="button"><i class="bi bi-check2-square"></i></button>
								<button class="btn btn-danger editCancel" type="button"><i class="bi bi-x-square"></i></button>
							</div>
						</div>
						<div id="collapse${ status.count }" class="panel-collapse collapse">
							<div class="panel-body">
								<c:forEach items="${ category.value }" var="post">
									<p class="postEvent" id="postNo.${ post.key }"><i class="bi bi-file-earmark-fill"></i> ${ post.value } </p>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div id="rightDiv">
		<%-- <jsp:include page="${ pageContext.request.contextPath }/../WEB-INF/views/${ includePage }.jsp"/> --%>
		<jsp:include page="${ pageContext.request.contextPath }/../WEB-INF/views/${ includePage }.jsp"/>
	</div>
</body>
</html>