<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- js import start -->
<script src="${ pageContext.request.contextPath }/resources/js/modify.js" type="text/javascript"></script>
<!-- js import end -->

<!-- css import start -->
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/modify.css">
<!-- css import end -->

<!-- 프로필 이미지 설정 관련 div -->
<div id="settingImgBox">
	<!-- 설정된 이미지가 뜨는 div -->
	<div id="imgBox">
		<div id="square">
			<c:choose>
				<c:when test="${ empty userDataMap.USER_IMG_EXP }">
					<img id="imgProfile" alt="${ userDataMap }" src="${ pageContext.request.contextPath }/resources/images/defaultProfile.jpg">
				</c:when>
				<c:otherwise>
					<img id="imgProfile" alt="errorDir" src="/diaret/resources/userData/${ userDataMap.USER_NUM }/profile.${ userDataMap.USER_IMG_EXP }">
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- 이미지 관련 버튼만 모아놓은 div -->
	<div id="inputImg">
		<form id="settingImgForm">
			<h1 id="title1">프로필 사진 변경</h1>
			<input id="srcImg" name="srcImg" type="file" class="form-control" accept="image/gif, image/jpeg, image/png" onchange="chk_file_type(this)"/>
			<div>
				<button id="initializeImg" type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">기본 프로필 사진 사용하기</button>
				<button id="applyImg" type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">이미지 저장</button>
			</div>
		</form>
	</div>
</div>
<!-- 프로필 수정 UI 중 텍스트 쪽만 담당하는 div -->
<div id="settingInfoBox">
	<h1 id="title2">회원 정보 변경</h1>
	<p id="subtitle" class="alert alert-info">"비밀번호"/"비밀번호 확인"을 모두 빈칸으로 남겨두면 기존 비밀번호를 계속 사용합니다.</p>
	<form id="settingInfoForm">
		<!-- 프로필 텍스트 정보 수정 UI 중 type=text만 모아놓은 div -->
		<div id="settingInfoTextBox">
			<div id="idAndPw">
				<input id="userId" name="userId" type="text" class="form-control" placeholder="아이디" readonly="readonly" value="${ userDataMap.USER_ID }"/>
				<input id="userPw" name="userPw" type="password" class="form-control" placeholder="변경할 비밀번호"/>
				<input id="userPwRe" name="userPwRe" type="password" class="form-control" placeholder="변경할 비밀번호 확인"/>
			</div>
			<div id="otherInfo">
				<input id="userName" name="userName" type="text" class="form-control" placeholder="성함" readonly="readonly" value="${ userDataMap.USER_NAME }"/>
				<input id="userEmail" name="userEmail" type="text" class="form-control" placeholder="이메일" value="${ userDataMap.USER_EMAIL }"/>
				<input id="userTel" name="userTel" type="text" class="form-control" placeholder="전화번호" value="${ userDataMap.USER_TEL }" maxlength="13"/>
				<input id="userNick" name="userNick" type="text" class="form-control" placeholder="닉네임" value="${ userDataMap.USER_NICK }"/>
			</div>
		</div>
		<!-- 프로필 텍스트 정보 수정 UI 중 button태그들만 모아놓은 div -->
		<div id="settingInfoBtnBox">
			<button id="doModify" type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">저장</button>
			<button id="cancel" type="button" class="btn btn-danger">취소</button>
			<button id="doWithdraw" type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">회원탈퇴</button>
		</div>
	</form>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">본인 확인</h4>
			</div>
			<div class="modal-body">
				<p>본인확인이 필요한 작업입니다.</p>
				<p>현재 비밀번호를 입력하세요.</p>
			</div>
			<div class="modal-footer">
				<form action="identifyForm">
					<input id="identifyInput" type="password" class="form-control" placeholder="현재 비밀번호" value="diaret2010"/>
					<button id="identifySubmit" type="button" class="btn btn-default" data-dismiss="modal">확인</button>
					<button id="identifyCancel" type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<input id="sessionUserPw" type="hidden" value="${ userDataMap.USER_PW }">
				</form>
			</div>
		</div>
	</div>
</div>