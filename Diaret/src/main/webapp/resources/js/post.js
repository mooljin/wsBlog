$(document).ready(function() {

	$("#newPost").click(function() {
		$("#postNum").val("0");
		$("#postOptionForm").attr("action", "goWrite.do");
		$("#postOptionForm").submit();
	});

	$("#modifyPost").click(function() {
		console.log($("#postNum").val());
		$("#postOptionForm").attr("action", "goWrite.do");
		$("#postOptionForm").submit();
	});

	$("#deletePost").click(function() {
		var checkDelete = confirm("정말 삭제하시겠습니까?");

		if(checkDelete) {
			//미구현
			$("#postOptionForm").attr("action", "doDelete.do");
			//submit안에 function을 넣으면 submit직전에 해야 할 일을 먼저 한 다음 submit을 한다.
			$("#postOptionForm").submit();
		}
	});
});