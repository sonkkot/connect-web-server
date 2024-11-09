ClassicEditor.create(document.querySelector('#project-write-ckeidtor-content'), {
  toolbar: [
    'undo', 'redo', 'bold', 'italic', 'strikethrough',
    'blockQuote', 'heading', 'link', 'numberedList', 'bulletedList',
  ],
}).then(editor => {
}).catch(error => {
  console.error(error);
});