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
	quill.getModule('toolbar').addHandler('image', function () {
	      selectLocalImage();
});

$("#test").click(()=>{console.log("buttonnnnn")})

quill.on('text-change', ()=>{quill.i});