const toolbarOptions = [
	[{ 'header': [1, 2, 3, 4, 5, 6, false] }],
    ['bold', 'italic', 'underline', 'strike'],
    [{ 'list': 'ordered'}, { 'list': 'bullet' }],
    [{ 'color': [] }, { 'background': [] }],
	[{'align':[]}],
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
	const file = convertBase64ImgToImgFile($("#img-0").attr("src"),"img-0.png")
	console.log(file)
	uploadImage(file)
	//console.log($("#form"))
})
const convertImage =(id) =>{
	axios.post('http://localhost/QuillTestProject/board/image_convert.do',
		{
			params:{image:"test blank"},
		},
		{
			responseType: 'blob',
		}).then((res)=>{
		console.log(res)
		$(`#${id}`).attr('src','http://localhost/QuillTestProject/board/image_convert.do')
	})
}
const uploadImage = (file) =>{//base64로 변환 후 axios로 formData전송해서 서버에 저장하고 url 반환하는 기능 만들기
	const formData = new FormData();
	formData.append("userfile", file);
	console.log(formData);
	/*axios.post(`/upload_multer_multi`, formData, 
	{
		headers: {
	   		"Content-Type": "multipart/form-data",
	    },
	});*/
	formData.forEach(function(value, key) {//form데이터 console.log로 출력하면 값 안나옴. 순회해서 출력해야됨
	    console.log(key + ': ' + value.size);
	});
}

	
const imageOnclick = (e) =>{
	console.log(e.target)
}
let imageTags=[];
let imageId=0;//있는 문서 수정하는 경우일 때는 미리 이미지 리스트 에서 마지막 이미지의 아이디 가져와서 설정하기
quill.on('text-change', function() {

	
	
	//quill에 추가된 요소중에 이미지만 리스트에 모으고 아이디를 추가하는 코드
	const changedImage = Array.from(quill.root.querySelectorAll("*"))
		    				.filter((child)=>{
								if(child.nodeName==='IMG'){
									if(child.id.length===0){
										child.id='img-'+imageId++;
										//convertImage(child.id)
										//console.log(convertBase64ImgToImgFile(child.src,child.id));
									}
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

const convertBase64ImgToImgFile = (data, fileName) => {
	const arr = data.split(',') // arr = [data:image/jpg;base64 , /9j/4AAQSkZJRgABAQAAAQABA ...]
	const [mime, binaryData] = [arr[0].match(/:(.*?);/)[1], atob(arr[1])] // atob는 base64 data를 decode한다
	// mime = image/jpg
	// binrayData = image의 binary data (atob는 window 내장 객체로 base64 data를 decode하는 메소드이다.)
	let n = binaryData.length 
	let unit8Array = new Uint8Array(n); 
	// binrayData를 다루기 위해 unit8Array Typpedarray 이용 
	// 이미지 데이터는 각 픽셀단위로 0~255로 표현되기 때문에 unit8Array를 이용하는 게 효율적 
	while (n--) {
    	unit8Array[n] = binaryData.charCodeAt(n) 
		// charCodeAt(n)은 array의 index=n 문자의 유니 코드 값을 반환한다.
	}
	return new File([unit8Array], fileName, { type: mime })
}

