$(document).ready(function() {

	$("#newPost").click(function() {
		$("#btnForm").attr("action", "goWrite.do");
		$("#btnForm").submit();
	});

	$("#modifyPost").click(function() {

		var category = $("#categoryTitle").text();
		var title = $("#postTitle").text();
		var content = $("#bottomDiv").html();
		var keywords = $("#bottomDiv").text();
		var data = {
			"category" : category,
			"title" : title,
			"content" : content,
			"keywords" : keywords
		};

		$.ajax({
			type: 'post',
			url: 'goWrite.do',
			data: data,
			async: false,
			success: function() {
				//요청이 중복으로 보내지거나, 한번만 나가는데 페이지 이동은 안됨.
				//요청이 한 번만 나가면서, 페이지 이동을 하길 원함.
//				$("#btnForm").attr("action", "goWrite.do");
//				$("#btnForm").submit();
			}
		});

	});
});