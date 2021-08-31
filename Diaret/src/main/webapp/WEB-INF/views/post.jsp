<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- js import start -->
<script src="${ pageContext.request.contextPath }/resources/js/post.js" type="text/javascript"></script>
<!-- js import end -->
<!-- css import start -->
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/post.css">
<!-- css import end -->
<!-- top UI -->
<div id="topDiv">
	<p id="categoryTitle"><i class="bi bi-folder-fill"></i>카테고리 샘플</p>
	<p id="postTitle">갓겜;;</p>
	<div id="displayFlex">
		<p id="postDate">2020.06.30(화) 18:20:03</p>
		<button id="newPost" type="button" class="btn btn-success">새 글</button>
		<button id="modifyPost" type="button" class="btn btn-success">수정</button>
		<button id="deletePost" type="button" class="btn btn-danger">삭제</button>
		<form id="btnForm"></form>
	</div>
</div>
<!-- bottom UI -->
<div id="bottomDiv">
	<!-- 게시글 내용(데이터 어케 만들지....?!) -->
	<span style="color: red;"> ㅋㅋㅋㅋㅋㅋ</span>
	<span> 인성질 당함 </span>
	<br>
	<img alt="fullguysSample.jpg" src="${ pageContext.request.contextPath }/resources/images/fullguysSample.jpg">
	<br>
	<span>건방진 놈 닉넴 기억했다...</span>
	<br>
	<img alt="fullguysSample.jpg" src="${ pageContext.request.contextPath }/resources/images/fullguysSample.jpg">
	<br>
	<img alt="fullguysSample.jpg" src="${ pageContext.request.contextPath }/resources/images/fullguysSample.jpg">
	<br>
</div>