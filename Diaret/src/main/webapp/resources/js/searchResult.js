$(document).ready(function() {
	var keyword = $("#hiddenKeyword").text().trim(); //request로 파라미터를 받았다는 전제하에 이렇게 둠.
	var keywordLength = keyword.length;
	console.log("검색 키워드 : " + keyword);
	console.log("길이 : " + keywordLength);

	$(".resultTitle, .resultOutline").each(function(index, item) {
		var wholeHtml = $(item).text();

		var firstIndex = wholeHtml.indexOf(keyword);

		if(firstIndex > -1) {
			var prefix = wholeHtml.substring(0, firstIndex);

			var suffix = wholeHtml.substring(firstIndex + keywordLength);

			var highlight = prefix + "<span class='highlight'>" + keyword + "</span>" + suffix;

			$(item).html(highlight);
		}
	});
});