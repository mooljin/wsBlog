$(document).ready( function() {

	$("#logout").click( function() {
		var checkLogout = confirm("로그아웃 하시겠습니까?");
		if(checkLogout) {
			$("#btnForm").attr("action", "doLogout.do");
			$("#btnForm").submit();
		}
	});

	$("#modify").click( function() {

		$("#btnForm").attr("action", "goModify.do");
		$("#btnForm").submit();
	});

	$(".postEvent").each( function(index, item) {
		$(item).click(function() {
			location.href = "goPost.do?" + $(item).attr("id").split(".")[1];
		});
	});
});