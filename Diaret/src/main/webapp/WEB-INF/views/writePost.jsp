<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- js import start -->
<script src="${ pageContext.request.contextPath }/resources/js/writePost.js" type="text/javascript"></script>
<!-- js import end -->

<!-- css import start -->
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/writePost.css">
<!-- css import end -->

<!-- 페이지 제목 및 저장 버튼 부분 -->
<div id="editor-menu1" class="editor-menu">
	<h1>게시글 작성</h1>
	<div>
		<button id="btn-bold" class="btn btn-success">저장</button>
	</div>
</div>
<!-- 제목 및 카테고리 설정 부분 -->
<div id="editor-title">
	<input id="title" name="title" class="form-control" type="text" placeholder="글 제목" value="${ title }"/>
	<div class="btn-group" id="category">
		<input id="categoryInput" type="text" class="form-control dropdown-toggle" data-toggle="dropdown" placeholder="카테고리(직접 입력 시 새로운 카테고리 생성)" value="${ category }"/>
		<ul class="dropdown-menu" role="menu">
			<li class="writeCategory">카테고리1</li>
			<li class="writeCategory">카테고리2</li>
			<li class="writeCategory">카테고리3</li>
		</ul>
	</div>
</div>
<!-- 글 서식 편집 버튼 모음 부분 -->
<div id="editor-menu2" class="editor-menu">
	<button id="btn-bold" class="btn"><b>B</b></button>
	<button id="btn-italic" class="btn"><i>I</i></button>
	<button id="btn-underline" class="btn"><u>U</u></button>
	<button id="btn-strike" class="btn"><s>S</s></button>
	<button id="btn-justify-full" class="btn"><i class="bi bi-justify"></i></button>
	<button id="btn-justify-left" class="btn"><i class="bi bi-text-left"></i></button>
	<button id="btn-justify-right" class="btn"><i class="bi bi-text-right"></i></button>
	<button id="btn-justify-center" class="btn"><i class="bi bi-text-center"></i></button>
	<button id="btn-ordered-list" class="btn"> <i class="bi bi-list-ol"></i> </button>
	<button id="btn-unordered-list" class="btn"> <i class="bi bi-list-ul"></i> </button>
	<button id="btn-image" class="btn"> <i class="bi bi-image"></i> </button>
	<input id="img-selector" type="file" accept="image/*"/>
</div>
<!-- 에디터 영역 : 편집이 가능한 div -->
<div id="editor" contenteditable="true">
	${ content }
</div>
