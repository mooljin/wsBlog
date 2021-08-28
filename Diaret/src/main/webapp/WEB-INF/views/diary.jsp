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
	<%-- <script src="${ pageContext.request.contextPath }/resources/js/main.js" type="text/javascript"></script> --%>
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
			<p id="nickname">사탄아저씨</p>
			<div id="alignCenter">
				<img id="imgProfile" alt="defaultProfile.jpg" src="${ pageContext.request.contextPath }/resources/images/fallguy.jpg">
			</div>
			<div id="btnProfile">
				<button type="button" class="btn btn-danger" id="settings"><i class="bi bi-box-arrow-left"></i></button>
				<button type="button" class="btn btn" id="modify"><i class="bi bi-gear-fill"></i></button>
			</div>
		</div>
		<div id="fixedLength">
			<div id="postings">
				<!-- 카테고리 및 게시글 목록 보이기 -->
				<div class="container">
					<div class="panel-heading">
						<h4 class="panel-title">
						<a class="fontSize18" data-toggle="collapse" data-parent="#accordion" href="#collapse1">
							<i class="bi bi-folder-fill"></i> 카테고리 샘플
						</a>
						</h4>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<div class="panel-body">
							<p><i class="bi bi-file-earmark-fill"></i> 게시글1 </p>
							<p><i class="bi bi-file-earmark-fill"></i> 게시글2 </p>
							<p><i class="bi bi-file-earmark-fill"></i> 게시글3 </p>
						</div>
					</div>
					<div class="panel-heading">
						<h4 class="panel-title">
						<a class="fontSize18" data-toggle="collapse" data-parent="#accordion" href="#collapse2">
							<i class="bi bi-folder-fill"></i> Left 4 Dead
						</a>
						</h4>
					</div>
					<div id="collapse2" class="panel-collapse collapse">
						<div class="panel-body">
							<p><i class="bi bi-file-earmark-fill"></i> 게시글4 </p>
							<p><i class="bi bi-file-earmark-fill"></i> 게시글5 </p>
							<p><i class="bi bi-file-earmark-fill"></i> 게시글6 </p>
						</div>
					</div>
					<div class="panel-heading">
						<h4 class="panel-title">
						<a class="fontSize18" data-toggle="collapse" data-parent="#accordion" href="#collapse3">
							<i class="bi bi-folder-fill"></i> 디아블로
						</a>
						</h4>
					</div>
					<div id="collapse3" class="panel-collapse collapse">
						<div class="panel-body">
							<p><i class="bi bi-file-earmark-fill"></i> Posting 4 </p>
							<p><i class="bi bi-file-earmark-fill"></i> 게시글5 </p>
							<p><i class="bi bi-file-earmark-fill"></i> 게시글6 </p>
						</div>
					</div>
					<div class="panel-heading">
						<h4 class="panel-title">
						<a class="fontSize18" data-toggle="collapse" data-parent="#accordion" href="#collapse4">
							<i class="bi bi-folder-fill"></i> 워프레임
						</a>
						</h4>
					</div>
					<div id="collapse4" class="panel-collapse collapse">
						<div class="panel-body">
							<p><i class="bi bi-file-earmark-fill"></i> 게시글4 </p>
							<p><i class="bi bi-file-earmark-fill"></i> 게시글5 </p>
							<p><i class="bi bi-file-earmark-fill"></i> 게시글6 </p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="searchBox">
		<!-- 검색 보이기 -->
			<form id="searchForm">
				<div class="btn-group">
					<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> 모두 <span class="caret"></span></button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">카테고리1</a></li>
						<li><a href="#">카테고리2</a></li>
					</ul>
				</div>
				<div id="inlineElements">
					<input type="text" class="form-control" name="search" placeholder="제목 또는 내용으로 검색"/>
					<button type="button" class="btn btn-info" id="submit">검색</button>
				</div>
			</form>
		</div>
	</div>
	<div id="rightDiv">
		<jsp:include page="${ pageContext.request.contextPath }/../WEB-INF/views/modify.jsp"/>
	</div>
</body>
</html>