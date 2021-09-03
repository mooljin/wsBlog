$(document).ready( function() {


	//로그아웃
	$("#logout").click( function() {
		var checkLogout = confirm("로그아웃 하시겠습니까?");
		if(checkLogout) {
			$("#btnForm").attr("action", "doLogout.do");
			$("#btnForm").submit();
		}
	});

	//개인정보 수정
	$("#modify").click( function() {

		$("#btnForm").attr("action", "goModify.do");
		$("#btnForm").submit();
	});

	//게시글 보기
	$(".postEvent").each( function(index, item) {
		$(item).click(function() {
			location.href = "goPost.do?postNum=" + $(item).attr("id").split(".")[1];
		});
	});

	//검색 옵션에 있는 카테고리를 누르면 버튼 내용을 검색하고자 하는 카테고리 이름으로 변경.
	$(".btn-group li").each(function(index, item) {
		$(item).click(function() {
			$("#selectCategory").html($(item).text() + " <span class=\"caret\"></span>");
			$("#postCategory").val($(item).text());
		});
	});

	$("#submit").click(function () {
		$("input[name=postKeywords]").val($("input[name=postKeywords]").val().trim());
	});

	//카테고리 이름 변경, 삭제 관련 기능
	$(".panel-heading").each(function(index, item) {

		var addHoveringEvent = function() {
			$(item).mouseover(function() {
				$(item).find(".btnEditCategory1").removeAttr("hidden");
			});

			$(item).mouseout(function() {
				$(item).find(".btnEditCategory1").attr("hidden", "true");
			});
		};

		addHoveringEvent();

		var previousCategory = "";
		var currentCategory = "";

		$(item).find(".editCategory").click(function() {
			previousCategory = $(item).find(".fontSize18").text().trim();
			$(item).find(".btnEditCategory2").removeAttr("hidden");
			$(item).find(".btnEditCategory1").attr("hidden", "true");

			$(item).find(".fontSize18").html("<input class='form-control editInput' name='editInput' placeholder='변경할 카테고리명'/>");
			$(item).find(".editInput").val(previousCategory);

			$(item).off("mouseover mouseout");
		});

		$(item).find(".editCancel").click(function() {
			$(item).find(".btnEditCategory1").removeAttr("hidden");
			$(item).find(".btnEditCategory2").attr("hidden", "true");

			$(item).find(".fontSize18").html("<i class='bi bi-folder-fill'></i>" + previousCategory);
			addHoveringEvent();
		});

		$(item).find(".editSubmit").click(function() {

			currentCategory = $(item).find(".editInput").val().trim();

			var data = {
				"previousCategory" : previousCategory,
				"currentCategory" : currentCategory
			}

			$.ajax({
				type: 'post',
				url: 'modifyCategory.do',
				data: data,
				async: true,
				success: function(isExistCategory) {
					if(isExistCategory == "T") {
						alert("이미 존재하는 카테고리 이름으로 변경하실 수 없습니다.");
						return;
					} else {
						//카테고리 이름 변경, 이벤트 원 상태로 되돌리기
						$(item).find(".btnEditCategory1").removeAttr("hidden");
						$(item).find(".btnEditCategory2").attr("hidden", "true");

						//목록으로 보여지는 카테고리 이름 변경
						$(item).find(".fontSize18").html("<i class='bi bi-folder-fill'></i>" + currentCategory);
						//검색 옵션으로 보여지는 카테고리 이름 변경
						//검색 콤보박스 옵션 중 해당 카테고리 제거
						$(".dropdown-menu").find("li").each(function(index, item) {
							if($(item).text().trim() == previousCategory) {
								$(item).html("<a href='#'>" + currentCategory + "</a>");
							}
						});
						previousCategory = currentCategory;
						addHoveringEvent();
					}
				}
			});
		});

		$(item).find(".deleteCategory").click(function() {

			previousCategory = $(item).find(".fontSize18").text().trim();

			var checkDelete = confirm("카테고리 내부에 있는 게시글까지 삭제됩니다.\n정말 삭제하시겠습니까?");

			if(checkDelete) {
				var data = {
					"previousCategory" : previousCategory,
				}

				$.ajax({
					type: 'post',
					url: 'deleteCategory.do',
					data: data,
					async: true,
					success: function() {
						//카테고리 UI중 카테고리에 속한 게시글 부분 삭제. (후손 태그까지 모두)
						$(item).next().remove();
						//카테고리 UI중 카테고리 제목 부분 삭제. (후손 태그까지 모두)
						//순서 주의. 삭제할 게 여러개 있다면 삭제할 것을 토대로 찾아낸 태그부터 삭제할 것.
						$(item).remove();

						//검색 콤보박스 옵션 중 해당 카테고리 제거
						$(".dropdown-menu").find("li").each(function(index, item) {
							if($(item).text().trim() == previousCategory) {
								$(item).remove();
							}
						});
						alert("카테고리를 삭제하였습니다.");

						//siblings() : 자신과 위치가 같은 형제 태그를 모두 가져온다. 부모가 달라도 들고온다.
						//siblings("#선택자") : 자신과 위치가 같은 형제 태그 중 해당 되는 id를 가진 형제 태그만 모두 가져온다.
						//next() : 자신과 위치가 같은 형제 태그 중 바로 다음에 위치하는 태그만 가져온다.
					}
				});
			}
		});
	});
});