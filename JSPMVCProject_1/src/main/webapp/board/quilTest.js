const toolbarOptions = [
	[{ 'header': [1, 2, 3, 4, 5, 6, false] }],
    ['bold', 'italic', 'underline', 'strike'],
    [{ 'list': 'ordered'}, { 'list': 'bullet' }],

    [{ 'color': [] }, { 'background': [] }],
    ['image', 'link'],

];
const options = {
	debug: false,
	modules: {
		toolbar: toolbarOptions,
	},
	placeholder: 'Compose an epic...',
	theme: 'snow'
};


const quill = new Quill('#editor', options);

$("#test").click(()=>{console.log("buttonnnnn")})

quill.on('text-change', function(delta, oldDelta, source) {
  if (source === 'user') {
    // Quill 문서의 모든 내용 가져오기
    var content = quill.root.innerHTML;
    
    // 이미지 태그가 포함된 부분만 추출
    var imageTags = content.match(/<img[^>]*>/g);
    
    if (imageTags) {
      //console.log('추가된 이미지 태그:', imageTags);
	  console.log(delta);
	  console.log(oldDelta);
    }
  }
});





