<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- js import start -->
<script src="${ pageContext.request.contextPath }/resources/js/searchResult.js" type="text/javascript"></script>
<!-- js import end -->

<!-- css import start -->
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/searchResult.css">
<!-- css import end -->

<div id="resultTitleBox">
	<h1 id="hiddenKeyword">${ param.postKeywords }</h1>
	<!-- parameter 접근 시 'param'을 명시 -->
	<c:choose>
		<c:when test="${ param.postCategory eq 'ALL' }">
			<h1 id="resultTitle">'모든' 카테고리의 '${ param.postKeywords }' 검색 결과</h1>
		</c:when>
		<c:otherwise>
			<h1 id="resultTitle">'${ param.postCategory }' 카테고리의 '${ param.postKeywords }' 검색 결과</h1>
		</c:otherwise>
	</c:choose>
</div>
<div id="wholeResultBox">
	<c:forEach items="${ postDataMap }" var="post">
		<div class="resultBox">
			<div class="result" id="searchNo.${ post.POST_NUM }">
				<div class="title">
					<p>${ post.POST_CATEGORY }</p>
					<h3 class="resultTitle">${ post.POST_TITLE }</h3>
					<p>${ post.POST_DATE }</p>
				</div>
				<div class="outline">
					<p class="resultOutline">${ post.POST_KEYWORDS }</p>
				</div>
			</div>
		</div>
	</c:forEach>
</div>