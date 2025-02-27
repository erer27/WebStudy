const toolbarOptions = [
	[{ 'header': [1, 2, 3, 4, 5, 6, false] }],
    ['bold', 'italic', 'underline', 'strike'],
    [{ 'list': 'ordered'}, { 'list': 'bullet' }],
    [{ 'color': [] }, { 'background': [] }],
	[{'align':[]}],
    ['image', 'link']
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

let imageTags=[];
let imageId=0;
quill.on('text-change', function(delta, oldDelta, source) {

	//quill에 추가된 요소중에 이미지만 리스트에 모으고 아이디를 추가하는 코드
	const changedImage = Array.from(quill.root.querySelectorAll("*"))
    				.filter((child)=>{
						if(child.nodeName==='IMG'){
							child.id=child.id.length===0?'img-'+imageId++:child.id;
							return true
						}
  						return false
  					})
	//imageTags 리스트 맨 마지막 요소가 방금 추가된 이미지라고 가정하고 서버랑 통신해서 src 바꾸거나 하기
  
	if(imageTags.length>changedImage.length){//방금 변화가 이미지 삭제일 경우
	
		const deletedImages=imageTags.filter((child)=>{
	  		return !changedImage.includes(child)
	  	})
	 	console.log("deletedImage",deletedImages)
	}
  
	imageTags=changedImage
	console.log("imageTags",imageTags)
});




