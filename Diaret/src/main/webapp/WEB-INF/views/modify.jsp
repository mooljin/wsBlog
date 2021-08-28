<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/modify.css">
<!-- 프로필 이미지 설정 관련 div -->
<div id="settingImgBox">
	<!-- 설정된 이미지가 뜨는 div -->
	<div id="imgBox">
		<div id="square">
			<img id="imgPreview" alt="defaultProfile.jpg" src="${ pageContext.request.contextPath }/resources/images/fallguy.jpg">
		</div>
	</div>
	<!-- 이미지 관련 버튼,만 모아놓은 div -->
	<div id="inputImg">
		<form id="settingImgForm">
			<h1 id="title1">프로필 사진 변경</h1>
			<input id="srcImg" type="text" class="form-control" placeholder="클릭하여 이미지 파일 찾기.." readonly="readonly"/>
			<div>
				<button id="deleteImg" type="button" class="btn btn-danger">기본 프로필 사진 사용하기</button>
				<button type="button" class="btn btn-success">적용!</button>
			</div>
		</form>
	</div>
</div>
<!-- 프로필 수정 UI 중 텍스트 쪽만 담당하는 div -->
<div id="settingInfoBox">
	<h1 id="title2">회원 정보 변경</h1>
	<form id="settingInfoForm">
		<!-- 프로필 텍스트 정보 수정 UI 중 type=text만 모아놓은 div -->
		<div id="settingInfoTextBox">
			<div id="idAndPw">
				<input type="text" class="form-control" placeholder="아이디" readonly="readonly"/>
				<input type="text" class="form-control" placeholder="현재 비밀번호"/>
				<input type="text" class="form-control" placeholder="변경할 비밀번호"/>
				<input type="text" class="form-control" placeholder="변경할 비밀번호 확인"/>
			</div>
			<div id="otherInfo">
				<input type="text" class="form-control" placeholder="성함" readonly="readonly"/>
				<input type="text" class="form-control" placeholder="이메일"/>
				<input type="text" class="form-control" placeholder="전화번호"/>
				<input type="text" class="form-control" placeholder="닉네임"/>
			</div>
		</div>
		<!-- 프로필 텍스트 정보 수정 UI 중 button태그들만 모아놓은 div -->
		<div id="settingInfoBtnBox">
			<button type="button" class="btn btn-success">저장</button>
			<button type="button" class="btn btn-danger">취소</button>
			<button type="button" class="btn btn-danger">회원탈퇴</button>
		</div>
	</form>
</div>