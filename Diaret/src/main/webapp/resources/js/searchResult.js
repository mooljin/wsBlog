$(document).ready(function() {
	var keyword = $("#hiddenKeyword").text().trim(); //request로 파라미터를 받았다는 전제하에 이렇게 둠.
	var keywordLength = keyword.length;

	//검색 내용 강조 및 내용 잘라내기
	$(".resultTitle, .resultOutline").each(function(index1, item1) {
		var wholeHtml = $(item1).text();

		var firstIndex = wholeHtml.indexOf(keyword);

		var maximumLength = 200;

		//현재 다루고 있는 item이 내용인가?
		if($(item1).attr("class") == "resultOutline") {
			//너무 길면 보여주는 내용을 200자 이하로 제한함.

			//내용이 200자가 넘는가?
			var isOverMaximumLength = wholeHtml.length > maximumLength;
			//첫 키워드 검색 지점부터 글자 수를 세어서 200자가 넘는가?
			var isOverMaximumLengthFromFirstIndex = wholeHtml.substring(firstIndex).length > maximumLength;
			if(isOverMaximumLength) {
				if(isOverMaximumLengthFromFirstIndex) {
					//넘으면 키워드로부터 200자까지만 보여줌.
					wholeHtml = wholeHtml.substring(firstIndex, firstIndex + maximumLength);
				} else {
					//안 넘으면 키워드부터 끝까지 보여줌.
					wholeHtml = wholeHtml.substring(firstIndex)
				}
			}
		}

		if(firstIndex > -1) {
			var splitedStr = wholeHtml.split(keyword);

			var highlight = splitedStr[0];

			$(splitedStr).each(function(index2, item2) {
				if(index2 != 0) {
					highlight += "<span class='highlight'>" + keyword + "</span>" + item2;
				}
			});

			if($(item1).attr("class") == "resultOutline") {
				if(firstIndex != 0 && isOverMaximumLength) {
					highlight = "..." + highlight;
				}
				if(isOverMaximumLengthFromFirstIndex) {
					highlight = highlight + "...";
				}
			}
			$(item1).html(highlight);
		} else {
			//키워드 검색 결과 제목때문에 검색된 경우 - 내용은 해당사항이 없지만 200자를 초과할 때
			if($(item1).attr("class") == "resultOutline") {
				if(isOverMaximumLength) {
					wholeHtml = wholeHtml.substring(0, maximumLength) + "...";
				}
			}
			$(item1).html(wholeHtml);
		}
	});

	//검색 결과로 나온 게시글을 누르면 해당 게시글로 이동
	$(".result").each( function(index, item) {
		$(item).click(function() {
			location.href = "goPost.do?postNum=" + $(item).attr("id").split(".")[1];
		});
	});
});