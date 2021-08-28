<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<!-- js import start -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="${ pageContext.request.contextPath }/resources/js/ui/jquery.json-2.2.min.js" type="text/javascript"></script>
	<script src="${ pageContext.request.contextPath }/resources/js/main.js" type="text/javascript"></script>
	<!-- js import end -->

	<!-- css import start -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/main.css">
	<!-- css import end -->

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>main.jsp</title>
	</head>

	<body>
		<p style="display:none" id="unvisibleValue">${ loginFailed }</p>
		<div id="mainDiv">
			<!-- left UI -->
			<div id="leftDiv" class="container">
					<div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="false" data-wrap="true">
						<!-- Indicators 선택자 -->
					    <ol class="carousel-indicators">
					    	<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					    	<li data-target="#myCarousel" data-slide-to="1"></li>
					    	<li data-target="#myCarousel" data-slide-to="2"></li>
					    </ol>

					    <!-- Wrapper for slides -->
					    <div class="carousel-inner">
					    	<!-- 슬라이드 1페이지 -->
					    	<div class="item slide1 active">
					    		<h1 class="firstStr">Diaret에서 소중한 순간들을</h1>
								<h1>기록하세요.</h1>
								<div class="tutorial_imgs">
									실제 사용하는 화면이 여기 들어가야 함.
									배경색은 투명하게 만들 예정.
								</div>
					    	</div>

							<!-- 슬라이드 2페이지 -->
					    	<div class="item slide2">
					        	<h1 class="firstStr">카테고리를 분류하여</h1>
					        	<h1>손쉽게 다이어리 관리를 할 수 있습니다.</h1>
					        	<div class="tutorial_imgs">
									실제 사용하는 화면이 여기 들어가야 함.
									배경색은 투명하게 만들 예정.
								</div>
					    	</div>

					    	<!-- 슬라이드 3페이지 -->
					    	<div class="item slide3">
					        	<h1 class="firstStr">카카오톡 연동으로</h1>
					        	<h1>빠르게 시작하세요!</h1>
					        	<div class="tutorial_imgs">
					        		실제 사용하는 화면이 여기 들어가야 함.
									배경색은 투명하게 만들 예정.
								</div>
					     	</div>
					    </div>
					</div>
			</div>

			<!-- right UI -->
			<div id="rightDiv">
				<p class="alert alert-info inNoteBook">환영합니다.</p>
				<form action="doLogin.do" method="post" id="loginForm">
					<input class="form-control inNoteBook" type="text" placeholder="아이디" name="userId" required/>
					<input class="form-control inNoteBook" type="password" placeholder="비밀번호" name="userPw" required/>
					<button type="button" class="btn btn-success inNoteBook" id="login">로그인</button>
					<button type="button" class="btn btn-success inNoteBook" id="join">회원가입</button>
					<button type="button" class="btn btn-warning inNoteBook" id="kakao">카카오톡으로 로그인</button>
				</form>
			</div>
		</div>
	</body>
</html>