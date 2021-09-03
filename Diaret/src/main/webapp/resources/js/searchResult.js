$(document).ready(function() {
	var keyword = $("#hiddenKeyword").text().trim(); //request로 파라미터를 받았다는 전제하에 이렇게 둠.
	var keywordLength = keyword.length;

	//검색 내용 강조 및 내용 잘라내기
	$(".resultTitle, .resultOutline").each(function(index1, item1) {
		var wholeHtml = $(item1).text();

		var maximumLength = 200;

		//대소문자 구분 없이(i) 키워드와 일치하는 모든 문자열(g)을 반환
		var regexAllCase = new RegExp(keyword, "gi");
		//일치하는 게 없으면 빈 배열이 아닌 null을 반환
		var matchKeywords = wholeHtml.match(regexAllCase);

		//일치하는 키워드 중 첫번째 키워드의 위치. 없으면 -1로 그대로 유지.
		var firstIndex = -1;
		if(matchKeywords != null) {
			var firstIndex = wholeHtml.indexOf(matchKeywords[0]);
		}

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

		if(matchKeywords != null) {

			var splitedStr = wholeHtml.split(regexAllCase);

			var highlight = splitedStr[0];

			$(splitedStr).each(function(index2, item2) {
				if(index2 != 0) {
					highlight += "<span class='highlight'>" + matchKeywords[index2 - 1] + "</span>" + item2;
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