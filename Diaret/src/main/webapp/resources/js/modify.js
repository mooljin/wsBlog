$(document).ready( function() {
	//modal 창 연결시키는 것만 쉬지 않고 했는데 오후 4시가 됨... 실화냐...
	//그래도 이렇게 만들어 두면 나중에 인증을 하고 싶지 않은 항목이 추가될 때 여기서 간단하게 수정할 수 있겠다.
	var eventSrc = "";

	//이벤트가 발생했던 버튼에 따라 요청으로 전송할 양식이 다륾.
	var chooseForm = function (form) {
		$("#userPw").val(btoa($("#userPw").val()));
		if($("#sessionUserPw").val() == btoa($("#identifyInput").val())) {
			//"저장 버튼을 누르고 모든 사용자 입력값이 조건에 부합할 때 추가로 해야 할 것들
			if(eventSrc == "doModify") {
				var data = $(form).serializeObject();
				//아래 위치에서 암호화를 하면 적용되지 않아서 8번줄에서 암호화를 진행함. 이유를 모르겠음.
//				data.userPw = btoa($("#userPw").val());
				//serializeObject()을 사용하려면 반드시 name 속성을 설정해야 한다.
				delete(data['userPwRe']);

				$.ajax({
					type: 'post',
					url: 'doModify.do',
					data: data,
					async: false,
					success: function() {
						alert("수정되었습니다.");
						$("#settingInfoForm").submit();
					}
				});
			}
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
		console.log($("#srcImg").val());
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
		if( !( $("#userEmail").val() && $("#userTel").val() && $("#userNick").val() ) ) {
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
			if( !( isBlank || isMatch) ) {
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

//첨부파일이 이미지 파일인지 검사
function chk_file_type(obj) {

	var file_kind = obj.value.lastIndexOf('.');
	var file_name = obj.value.substring(file_kind+1,obj.length);
	var file_type = file_name.toLowerCase();
	var check_file_type=['jpg','gif','png','jpeg','bmp','tif'];

	if(check_file_type.indexOf(file_type)==-1) {
		alert('이미지 파일만 업로드를 할 수 있습니다.');
//		//parentNode : 이 객체의 부모 노드를 들고 온다. 여기서는 #settingImgForm.
//		var parent_Obj=obj.parentNode;
//		//replaceChild(교체할 대상, 교체될 대상) : 자신의 자식 노드를 교체한다.
//		//cloneNode(true) : 해당 노드를 복제한다. 자식 노드까지 복사하려면 true.
//		//#imgSrc의 자식 노드는 없으므로 여기선 false로 줘도 상관 없음.
//		var node=parent_Obj.replaceChild(obj.cloneNode(true),obj);
//
//		//이건 솔직히 복붙한 코드인데.. 왜 이렇게까지 한 거지? 이거 없어도 잘 돌아감.

		document.getElementById("srcImg").value = "";    //초기화를 위한 추가 코드
	} else {
		//되긴 되는데 왜 되지? 잠시만...
		var input = document.getElementById("srcImg");
		//파일을 읽도록 해주는 객체?
		var fReader = new FileReader();
//		readAsDataURL 메서드는 컨텐츠를 특정 Blob 이나 File에서 읽어오는 역할을 합니다.
//		읽어오는 read 행위가 종료되는 경우에, readyState (en-US) 의 상태가 DONE이 되며,
//		loadend (en-US) 이벤트가 트리거 됩니다.
//		이와 함께,  base64 인코딩 된 스트링 데이터가 result  속성(attribute)에 담아지게 됩니다.
		fReader.readAsDataURL(input.files[0]);
		fReader.onloadend = function(event){
		    var img = document.getElementById("imgPreview");
		    //이벤트가 발생한 blob, img와 같은 대상을 인코딩 등으로 가공한 결과를 src에 넣는다.
		    img.src = event.target.result;
		}
	}
}