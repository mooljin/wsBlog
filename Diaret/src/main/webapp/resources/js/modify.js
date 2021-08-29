$(document).ready( function() {
	//modal 창 연결시키는 것만 쉬지 않고 했는데 오후 4시가 됨... 실화냐...
	//그래도 이렇게 만들어 두면 나중에 인증을 하고 싶지 않은 항목이 추가될 때 여기서 간단하게 수정할 수 있겠다.
	var eventSrc = "";

	//이벤트가 발생했던 버튼에 따라 요청으로 전송할 양식이 다륾.
	var chooseForm = function (form) {
		if($("#sessionUserPw").val() == btoa($("#identifyInput").val())) {
			$(form).submit();
		} else {
			alert("비밀번호가 다릅니다.");
		}
	};

	$("#identifySubmit").click( function() {
		if(eventSrc == "doModify" || eventSrc == "doWithdraw") {
			//text쪽 본인 확인 후 진행되는 로직
			chooseForm("#settingInfoForm");
		} else if(eventSrc == "initializeImg" || eventSrc == "applyImg") {
			//이미지 쪽 본인 확인 후 진행되는 로직
			chooseForm("#settingImgForm");
		}
	});

	//"클릭하여 이미지 파일 찾기.." text 칸 누를 시
	$("#srcImg").click( function() {

	});

	//"기본 프로필 사진 사용하기" 버튼 누를 시
	$("#initializeImg").click( function() {
		eventSrc = event.target.id;
		$("#settingImgForm").attr("action", "initializeImg.do");
	});

	//"이미지 저장" 버튼 누를 시
	$("#applyImg").click( function() {
		eventSrc = event.target.id;
		if($.trim($("#srcImg").val()) == "") {
			$(".modal").removeAttr("id");
			alert("프로필로 등록할 이미지 파일을 선택하세요.");
		} else {
			$(".modal").attr("id", "myModal");
			$("#settingImgForm").attr("action", "applyImg.do");
		}
	});

	//"저장" 버튼 누를 시
	$("#doModify").click( function() {
		eventSrc = event.target.id;
		//required 속성 사용 결과 불가능하므로 여기서 유효성 체크

		var valMsg = "";

		//1. 이메일, 전화번호, 닉네임 공란 체크
		if( !( $("#userEmail").val() && $("#userTel").val() && $("#userNickname").val() ) ) {
			valMsg = "이메일, 전화번호, 또는 닉네임이 빈칸인지 확인하세요.";
		}

		//2. 이메일 정규식 체크.
		//"valMsg가 빈 문자열"이라 함은 "여기 유효성 체크에 걸리는 것이 없다"는 의미이다.
		if(!(valMsg)) {
			var emailVal = $("#userEmail").val();
			var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

			if ( !(emailVal.match(regExp)) ) {
				valMsg = "이메일 형식에 맞게 입력하세요.";
			}
		}

		//3. 전화번호 정규식 체크
		if(!(valMsg)) {
			var telVal = $("#userTel").val();
			var regExp = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}/;

			if ( !(telVal.match(regExp)) ) {
				valMsg = "전화번호 형식에 맞게 입력하세요.\n(숫자 11자리, - 포함)";
			}
		}

		//4. 비밀번호/비밀번호 확인 둘 중 하나라도 공란이 아니라면 비밀번호 일치 체크
		if(!(valMsg)) {
			var isBlank = $("#userPw").val() && $("#userPwRe").val();
			var isMatch = $("#userPw").val() == $("#userPwRe").val();
			if( !( isBlank && isMatch) ) {
				valMsg = "새로 사용할 비밀번호가 서로 일치한지 확인하세요.";
			}
		}

		//valMsg 내용이 있으면 유효하지 않은 요청
		if(valMsg) {
			$(".modal").removeAttr("id");
			alert(valMsg);
		} else {
			$(".modal").attr("id", "myModal");
			$("#settingInfoForm").attr("action", "doModify.do");
		}
	});

	//"취소" 버튼 누를 시
	$("#cancel").click( function() {
		$("#settingInfoForm").attr("action", "cancelModify.do");
		$("#settingInfoForm").submit();
	});

	//"회원 탈퇴" 버튼 누를 시
	$("#doWithdraw").click( function() {
		eventSrc = event.target.id;
		$(".modal").attr("id", "myModal");
		$("#settingInfoForm").attr("action", "doWithdraw.do");
	});
});