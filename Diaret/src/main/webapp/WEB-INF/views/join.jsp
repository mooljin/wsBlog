<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<!-- js import start -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="${ pageContext.request.contextPath }/resources/js/join.js" type="text/javascript"></script>
	<!-- js import end -->

	<!-- css import start -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/join.css">
	<!-- css import end -->

	<head>
		<meta charset="UTF-8">
		<title>join.jsp</title>
	</head>
	<body>
		<p class="alert alert-info">모든 항목은 필수 입력 사항입니다.</p>
		<hr>
		<form action="test.do" method="post">
			<input type="text" class="form-control" placeholder="아이디" name="id"/>
			<input type="password" class="form-control" placeholder="비밀번호" name="pw"/>
			<input type="password" class="form-control" placeholder="비밀번호 확인" name="re"/>
			<input type="text" class="form-control" placeholder="성함" name="name"/>
			<input type="text" class="form-control" placeholder="이메일" name="email"/>
			<input type="text" class="form-control" placeholder="전화번호" name="tel"/>
			<input type="text" class="form-control" placeholder="닉네임" name="nickname"/>
			<br>
			<button type="submit" class="btn btn-success">가입</button>
			<button class="btn btn-danger">취소</button>
		</form>
	</body>
</html>