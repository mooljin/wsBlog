$(document).ready(function() {

	$("#newPost").click(function() {
		$("#btnForm").attr("action", "goWrite.do");
		$("#btnForm").submit();
	});

	$("#modifyPost").click(function() {

		var category = $("#categoryTitle").text();
		var title = $("#postTitle").text();
		var content = $("#bottomDiv").html();
		var data = {
			"category" : category,
			"title" : title,
			"content" : content
		};

		$.ajax({
			type: 'post',
			url: 'goWrite.do',
			data: data,
			async: false,
			success: function() {
				location.replace("goWrite.do");
			}
		});

	});
});