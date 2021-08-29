$(document).ready(function() {

	//좀 모르겠는데...
	$("#unvisibleValue").change(function () {
		if($("#unvisibleValue").text() == "failed") {
			alert("아이디 혹은 비밀번호가 다릅니다.");
		}
	});

	//휠 클릭 시 슬라이드 전환
	$(".item").on("wheel", function (event){

		//to_do_list : 마우스 반대로 빠르게 변경시 슬라이드가 꼬이는 현상 발생
		if (event.originalEvent.deltaY < 0) {
			// wheeled up
			 $("#myCarousel").carousel("prev");
			 event.preventDefault();

		} else {
			// wheeled down
			$("#myCarousel").carousel("next");
			event.preventDefault();
		}
	});

	//회원가입 팝업창
	$("#join").click(function(){
		var screenWidth = screen.width;
		var screenHeight = screen.height;
		var popup = window.open(
			"goJoin.do",
			"join",
			"width=500, height=630, left=" + ((screenWidth - 500) / 2) + ", top=" + ((screenHeight - 630) / 2) + ", scrollbars=no, toolbar=0, menubar=no"
		);
	});

	//로그인 로직 - 비번 암호화하고 요청
	$("#login").click(function() {
		$("input[name=userPw]").val(btoa($("input[name=userPw]").val()));
		$("#loginForm").submit();
	});
});