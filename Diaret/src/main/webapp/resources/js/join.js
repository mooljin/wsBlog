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
		if($("input[name=userPw]").val() == $("input[name=userPwRe]").val()) {
			saveFg = false;
			valMsg = "비밀번호가 일치하지 않습니다.";
		}

		// input 공백체크, 벨리데이션 체크
		// todo_list : 유효성체크
		$("#joinForm").find("input").each(function(index, item) {
			if($.trim($(item).val()) == "") {
				saveFg = false;
				valMsg = $(item).attr("placeholder") + "를 입력하세요";
				return false;
			} else { // 유효성 체크
				if($(item).attr("name") == "userEmail") {
					saveFg = false;
				} else if($(item).attr("name") == "userTel") {
					saveFg = false;
				}
			}
		});

		// 아이디 중복체크
		var data = $("#joinForm").find("input").serializeObject();

		if(!($.trim($("input[name='userId']").val()) == "" || $.trim($("input[name='userId']").val()) == null || $.trim($("input[name='userId']").val()) == undefined)) {
			$.ajax({
				type: 'post',
				url: 'checkId.do',
				data: data,
				async: false,
				success: function(isExistId) {
					if(isExistId == "T") {
						valMsg = "중복된 아이디가 존재합니다.";
						saveFg = false;
					}
				}
			});
		}

		if(!saveFg) {
			alert(valMsg);
			return;
		} else {
			// 저장로직
		}
	});

});