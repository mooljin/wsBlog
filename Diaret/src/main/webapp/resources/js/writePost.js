$(document).ready(function() {
	const editor = document.getElementById('editor');
	const btnBold = document.getElementById('btn-bold');
	const btnItalic = document.getElementById('btn-italic');
	const btnUnderline = document.getElementById('btn-underline');
	const btnStrike = document.getElementById('btn-strike');
	const btnJustifyFull = document.getElementById('btn-justify-full');
	const btnJustifyRight = document.getElementById('btn-justify-right');
	const btnJustifyLeft = document.getElementById('btn-justify-left');
	const btnJustifyCenter = document.getElementById('btn-justify-center');
	const btnOrderedList = document.getElementById('btn-ordered-list');
	const btnUnorderedList = document.getElementById('btn-unordered-list');

	const btnImage = document.getElementById('btn-image');
	const imageSelector = document.getElementById('img-selector');

	const categories = $(".writeCategory");

	btnBold.addEventListener('click', function() {
		setStyle('bold');
	});
	btnItalic.addEventListener('click', function() {
		setStyle('italic');
	});
	btnUnderline.addEventListener('click', function() {
		setStyle('underline');
	});
	btnStrike.addEventListener('click', function() {
		setStyle('strikeThrough');
	});
	btnJustifyFull.addEventListener('click', function() {
		setStyle('justifyFull');
	});
	btnJustifyRight.addEventListener('click', function() {
		setStyle('justifyRight');
	});
	btnJustifyLeft.addEventListener('click', function() {
		setStyle('justifyLeft');
	});
	btnJustifyCenter.addEventListener('click', function() {
		setStyle('justifyCenter');
	});

	btnOrderedList.addEventListener('click', function() {
		setStyle('insertOrderedList');
	});
	btnUnorderedList.addEventListener('click', function() {
		setStyle('insertUnorderedList');
	});

	function setStyle(style) {
		var isEdited = document.execCommand(style);
		// 버튼 클릭 시 에디터가 포커스를 잃기 때문에 다시 에디터에 포커스를 해줌
		editor.focus({
			// 새로 포커스를 맞춘 요소를 보기 위해 스크롤을 하는데, 이 스크롤 행위를 막을 것인지를 결정하는 옵션.
			// true면 포커스가 들어와도 스크롤이 되지 않음.
			preventScroll : true
		});
	}

	btnImage.addEventListener('click', function() {
		imageSelector.click();
	});

	//기존에 있던 카테고리를 누르면 값이 변함.
	categories.each(function(index, item) {
		item.addEventListener('click', function() {
			$("#categoryInput").val($(item).text());
		});
	});

	imageSelector.addEventListener('change', function(e) {
		const files = e.target.files;
		// !! : undefined, "", 0이면 false. 나머지는 true.
		if (!!files) {
			insertImageDate(files[0]);
		}
	});

	function insertImageDate(file) {
		var file_kind = imageSelector.value.lastIndexOf('.');
		var file_name = imageSelector.value.substring(file_kind + 1,
				imageSelector.length);
		var file_type = file_name.toLowerCase();
		var check_file_type = [ 'jpg', 'gif', 'png', 'jpeg', 'bmp',
				'tif' ];

		if (check_file_type.indexOf(file_type) == -1) {
			alert('이미지 파일만 업로드를 할 수 있습니다.');
		} else {
			const reader = new FileReader();
			reader.addEventListener('load', function(e) {
				editor.focus({
					preventScroll : true
				});
				//bool = document.execCommand(aCommandName, aShowDefaultUI, aValueArgument)
				/*
				 * HTML 문서가 designMode로 전환되면, 즉 편집 가능한 상태라면 이 함수를 사용할 수 있다.
				 * 이 함수는 문서의 편집 가능한 영역에 있는 요소들을 편집할 수 있도록 해준다.
				 * 파라미터
				 * aCommandName : 실행할 명령어. 사용 가능한 명령어는 검색이 필요함.
				 * aShowDefaultUI : 기본 사용자 UI를 나타내고 싶다면 true.
				 * aValueArgument : 명령어에 따라 파라미터가 필요할 수 있음. 필요없다면 null, 필요하다면 넣어줄 것.
				 * 반환값 bool : 정상적으로 적용되었다면 true, 명령어가 적절하지 않거나 실패한 경우 false.
				 * */
				document.execCommand('insertImage', false, `${reader.result}`);
			});
			reader.readAsDataURL(file);
		}
	}

	$("#savePost").click(function() {
		var errMsg = "";

		//공란 검사
		//제목
		if(!$("#titleInput").val().trim()) {
			errMsg = "제목을 입력하세요.";
		}

		//카테고리
		if(!errMsg && !$("#categoryInput").val().trim()) {
			errMsg = "카테고리를 입력하세요.";
		}

		//내용 - 사진도 없고 텍스트도 없으면 공란으로 판단, 둘 중 하나라도 있으면 공란이 아님.
		if(!errMsg && $("#editor").find("img").length == 0 && !$("#editor").text().trim()) {
			errMsg = "내용을 입력하세요.";
		}

		if(errMsg) {
			alert(errMsg);
		} else {
			$("#postForm").attr("action", "savePost.do");
			$("#contentHtml").val($("#editor").html());
			console.log($("#contentHtml").val());

//			$("#postForm").submit();
		}
	});
});
