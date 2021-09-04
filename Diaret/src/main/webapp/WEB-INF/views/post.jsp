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
	<p id="categoryTitle"><i class="bi bi-folder-fill"></i>${ focusedPost.POST_CATEGORY }</p>
	<p id="postTitle">${ focusedPost.POST_TITLE }</p>
	<div id="displayFlex">
		<p id="postDate">${ focusedPost.POST_DATE }</p>
		<form id="postOptionForm">
			<input id="postNum" type="hidden" name="postNum" value="${ focusedPost.POST_NUM }"/>
			<button id="newPost" type="button" class="btn btn-success">새 글</button>
			<button id="modifyPost" type="button" class="btn btn-success">수정</button>
			<button id="deletePost" type="button" class="btn btn-danger">삭제</button>
		</form>
	</div>
</div>
<!-- bottom UI -->
	<!-- 게시글 내용 불러오기. -->
	<!-- "${ pageContext.request.contextPath }/../resources/userData/${ userDataMap.USER_NUM }/posts/1.jsp"와 -->
	<!-- "${ pageContext.request.contextPath }/userData/${ userDataMap.USER_NUM }/posts/1.jsp" 의 차이? -->

	<!-- 아래는 최종 버전 -->
<div id="bottomDiv">
	<%-- <jsp:include page="${ pageContext.request.contextPath }/../resources/userData/${ focusedPost.POST_USER_NUM }/posts/${ focusedPost.POST_NUM }.jsp"/> --%>
</div>