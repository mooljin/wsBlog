$(document).ready( function() {
	// 회원가입 팝업 취소
	$(".btn-danger").click( function() {
		self.close();
	});

	// 회원가입
	$("#btnJoin").click(function() {
		var saveFg = true; // false : 저장 성공, true : 저장 실패
		var valMsg = "";


		// 비밀번호 일치 체크
		if($("input[name=userPw]").val() != $("input[name=userPwRe]").val()) {
			saveFg = false;
			valMsg = "비밀번호가 일치하지 않습니다.";
		}

		// input 공백체크, 벨리데이션 체크
		$("#joinForm").find("input").each(function(index, item) {
			if($.trim($(item).val()) == "") {
				saveFg = false;
				valMsg = $(item).attr("placeholder") + "를 입력하세요";
				return false;
			} else { // 유효성 체크
				if($(item).attr("name") == "userEmail") {

					var emailVal = $(item).val();
					var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

					//정규식에 부합하는 문자열이 있으면 유효한 이메일
					if (emailVal.match(regExp) == null) {
						valMsg = "이메일 형식에 맞게 입력하세요.";
						saveFg = false;
					}

				} else if($(item).attr("name") == "userTel") {

					var telVal = $(item).val();
					var regExp = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}/;

					if (telVal.match(regExp) == null) {
						valMsg = "전화번호 형식에 맞게 입력하세요.\n(숫자 11자리, - 포함)";
						saveFg = false;
					}
				}
			}
		});

		//위 조건이 만족하면 아이디 중복 검사 시작
		if(!saveFg) {
			alert(valMsg);
			return;
		} else {
			//아이디 중복 검사 후 중복이면 가입 불가 아니면 바로 가입 완료
			var data = $("#joinForm").find("input").serializeObject();
			data.userPw = btoa($("input[name=userPw]").val());
			delete(data['userPwRe']);

			if(!($.trim($("input[name='userId']").val()) == "" || $.trim($("input[name='userId']").val()) == null || $.trim($("input[name='userId']").val()) == undefined)) {
				$.ajax({
					type: 'post',
					url: 'doJoin.do',
					data: data,
					async: false,
					success: function(isExistId) {
						if(isExistId == "T") {
							alert("중복된 아이디가 존재합니다.");
							return;
						} else {
							//to_do_List : 가입 완료 시 알림창이 main페이지에서 띄워야 함.
							//모르게써여...흑흑...
							alert("가입이 완료되었습니다.");
							self.close();
						}
					}
				});
			}
		}
	});

});