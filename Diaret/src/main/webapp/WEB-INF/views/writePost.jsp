<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- js import start -->
<script src="${ pageContext.request.contextPath }/resources/js/writePost.js" type="text/javascript"></script>
<!-- js import end -->

<!-- css import start -->
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/writePost.css">
<!-- css import end -->

<form id="postForm" method="post" enctype="multipart/form-data">
	<!-- 페이지 제목 및 저장 버튼 부분 -->
	<div id="editor-menu1" class="editor-menu">
		<h1>게시글 작성</h1>
		<div>
			<button id="savePost" type="button" class="btn btn-success">저장</button>
		</div>
	</div>
	<!-- 제목 및 카테고리 설정 부분 -->
	<div id="editor-title" class="dropdown">
		<input id="titleInput" name="title" class="form-control" type="text" placeholder="글 제목" value="${ postDataMap.POST_TITLE }"/>
		<input id="categoryInput" name="category" type="text" class="form-control dropdown-toggle" data-toggle="dropdown" placeholder="카테고리(직접 입력 시 새로운 카테고리 생성)" value="${ postDataMap.POST_CATEGORY }"/>
		<ul class="dropdown-menu" role="menu">
			<c:forEach items="${ classifiedPostData }" var="category" varStatus="status">
				<li class="dropdown-item writeCategory"><a href="#">${ category.key }</a></li>
			</c:forEach>
		</ul>
	</div>
	<!-- 글 서식 편집 버튼 모음 부분 -->
	<div id="editor-menu2" class="editor-menu">
		<button id="btn-bold" type="button" class="btn"><b>B</b></button>
		<button id="btn-italic" type="button" class="btn"><i>I</i></button>
		<button id="btn-underline" type="button" class="btn"><u>U</u></button>
		<button id="btn-strike" type="button" class="btn"><s>S</s></button>
		<button id="btn-justify-full" type="button" class="btn"><i class="bi bi-justify"></i></button>
		<button id="btn-justify-left" type="button" class="btn"><i class="bi bi-text-left"></i></button>
		<button id="btn-justify-right" type="button" class="btn"><i class="bi bi-text-right"></i></button>
		<button id="btn-justify-center" type="button" class="btn"><i class="bi bi-text-center"></i></button>
		<button id="btn-ordered-list" type="button" class="btn"> <i class="bi bi-list-ol"></i> </button>
		<button id="btn-unordered-list" type="button" class="btn"> <i class="bi bi-list-ul"></i> </button>
		<button id="btn-image" type="button" class="btn"> <i class="bi bi-image"></i> </button>
		<input id="img-selector" type="file" accept="image/*"/>
	</div>
	<!-- 에디터 영역 : 편집이 가능한 div -->
	<div id="editor" contenteditable="true">
		${ postDataMap.POST_CONTENT }
	</div>
	<input id="contentText" type="hidden" name="contentText"/>
	<input id="contentHtml" type="hidden" name="contentHtml"/>
</form>
