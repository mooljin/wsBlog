$(document).ready(function() {
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
		console.log(screenWidth);
		console.log(screenHeight);
		var popup = window.open(
			"goJoin.do",
			"join",
			"width=400, height=630, left=1500, top=" + ((screenHeight - 500) / 2) + ", scrollbars=no, toolbar=0, menubar=no"
		);
	});
});