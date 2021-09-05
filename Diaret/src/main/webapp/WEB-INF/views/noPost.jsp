<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- css import start -->
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/noPost.css">
<!-- css import end -->
<div>
	<p class="firstGuideText">wellCome!</p>
	<p class="firstGuideText">새로운 첫 순간을 기록해 보아요.</p>
	<form action="goWrite.do">
		<button id="btnNewFirstPosting" type="submit" class="btn btn-success">새글 작성</button>
	</form>
</div>