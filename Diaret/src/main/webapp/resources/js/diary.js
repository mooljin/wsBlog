$(document).ready( function() {

	$("#logout").click( function() {

	});

	$("#modify").click( function() {

		$("#btnForm").attr("action", "goModify.do");
		$("#btnForm").submit();
	});

});