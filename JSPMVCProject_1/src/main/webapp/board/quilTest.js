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

$("#test").click(()=>{
	$('#img-0').css('border','10px solid red')
	})

quill.on('text-change', function(delta, oldDelta, source) {
  /*if (source === 'user') {
    // Quill 문서의 모든 내용 가져오기
    var content = quill.root.innerHTML;
    
    // 이미지 태그가 포함된 부분만 추출
    var imageTags = content.match(/<img[^>]*>/g);
    
    if (imageTags) {
      //console.log('추가된 이미지 태그:', imageTags);
	  console.log(delta);
	  console.log(oldDelta);
    }
  }*/
  //const imageTags = Array.from(quill.root.querySelectorAll("*")).filter((child)=>{child.nodeName==='IMG'})
  //console.log(quill.root.querySelectorAll("*").forEach((child)=>{console.log(child.nodeName==='IMG')}))
  let imageId=0;
  
  //quill에 추가된 요소중에 이미지만 리스트에 모으고 아이디를 추가하는 코드
  const imageTags = Array.from(quill.root.querySelectorAll("*"))
    				.filter((child)=>{
  						return child.nodeName==='IMG'?true:false
  					}).map((child)=>{
						child.id='img-'+imageId++;
						return child
					})

  console.log(imageTags)
  console.log(quill.root)
  
});

const stringToHTML = function (str) {
  const dom = document.createElement("div");
  dom.innerHTML = str;
  return dom;
};
//최근에 삭제된 요소가 이미지인 경우를 포착




