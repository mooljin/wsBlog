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
				document.execCommand('insertImage', false, `${reader.result}`);
			});
			reader.readAsDataURL(file);
		}
	}
});

// function chk_file_type(obj) {
//
// var file_kind = obj.value.lastIndexOf('.');
// var file_name = obj.value.substring(file_kind+1,obj.length);
// var file_type = file_name.toLowerCase();
// var check_file_type=['jpg','gif','png','jpeg','bmp','tif'];
//
// if(check_file_type.indexOf(file_type)==-1) {
// alert('이미지 파일만 업로드를 할 수 있습니다.');
// } else {
// insertImageDate(file);
// }
// }
