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
	<%-- <link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/main.css"> --%>
	<!-- css import end -->
<head>
<meta charset="UTF-8">
<title>diary.jsp</title>
</head>
<body>
	<p>표현식 테스트 : <%= request.getAttribute("userData").toString() %></p>
	<p>EL 테스트 : ${ userData.userId }</p>
</body>
</html>